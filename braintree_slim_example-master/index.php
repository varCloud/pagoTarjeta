<?php
require 'vendor/autoload.php';
require_once("includes/braintree_init.php");

$gateway = new Braintree_Gateway([
    'environment' => getenv('BT_ENVIRONMENT'),
    'merchantId' => getenv('BT_MERCHANT_ID'),
    'publicKey' => getenv('BT_PUBLIC_KEY'),
    'privateKey' => getenv('BT_PRIVATE_KEY')
]);
$app = new \Slim\Slim();

$app->config([
    'templates.path' => 'templates',
]);

$app->get('/', function () use ($app) {
    //$app->redirect('/checkouts');
    echo "Iniciacion servicios con REST , SLIM y PHP";
});

$app->get('/token', function () use ($app, $gateway) {
    /*$app->render('checkouts/new.php', [
        'client_token' => $gateway->clientToken()->generate(),
    ]);
    */
    //$token => $gateway->clientToken()->generate();
    $clientToken = $gateway->clientToken()->generate();
    echo $clientToken;
});

$app->post('/verificarTarjeta', function () use ($app, $gateway) {
    $token = $app->request->post('token');
    $result = $gateway->paymentMethod()->create([
        'customerId' => $token,
        'paymentMethodNonce' => nonceFromTheClient,
        'options' => [
            'verifyCard' => true
        ]
    ]);

    echo json_encode($result);

});

$app->post('/crearMetodoPago', function () use ($app, $gateway) {
     $token = $app->request->post('token');
      $result = $gateway->paymentMethod()->create([
    'customerId' => $token,
    'paymentMethodNonce' => 'MetodoPago',
    'options' => [
      'makeDefault' => true
    ]
]);

    echo json_encode($result);

});


$app->post('/addCard', function () use ($app, $gateway) {
    $customerId = $app->request->post('token');
    $result = $gateway->creditCard()->create([
        'customerId' => $customerId,
        'number' => '378282246310005',
        'expirationDate' => '06/22',
        'cvv' => '3012'  ,
        'options' => [
          'makeDefault' => true,
           'verificationAmount' => 1,
           'verifyCard' => true
        ]
    ]);

    echo json_encode($result);

});

$app->post('/deleteCard', function () use ($app, $gateway) {
    $tokenTarjeta = $app->request->post('tokenTarjeta');
    $gateway->creditCard()->delete($tokenTarjeta);
     echo json_encode('ok');

});


$app->post('/addCiente', function () use ($app, $gateway) {
    $customerId = $app->request->post('token');
    $result = $gateway->customer()->create([
        'firstName' => 'Mike',
        'lastName' => 'Jones',
        'company' => 'Jones Co.',
        'email' => 'mike.jones@example.com',
        'phone' => '281.330.8004',
        'fax' => '419.555.1235',
        'website' => 'http://example.com'
    ]);


    echo json_encode($result);

});

$app->post('/getCliente', function () use ($app, $gateway) {
    $customerId = $app->request->post('token');
    $customer = $gateway->customer()->find($customerId);
    echo json_encode($customer);

});


$app->post('/getMetodoPago', function () use ($app, $gateway) {
    $token = $app->request->post('token');
    $paymentMethod = $gateway->paymentMethod()->find($token);
     echo json_encode($paymentMethod);

});



$app->post('/Pagar', function () use ($app, $gateway) {
    echo $app->request->post('monto');
    /*
    $result = $gateway->transaction()->sale([
        "amount" => $app->request->post('monto'),
        "paymentMethodNonce" => $app->request->post('payment_method_nonce'),
        'options' => [
            'submitForSettlement' => True
        ]
    ]);
    */
});


$app->post('/checkouts', function () use ($app, $gateway) {
    $result = $gateway->transaction()->sale([
        "amount" => $app->request->post('amount'),
        "paymentMethodNonce" => $app->request->post('payment_method_nonce'),
        'options' => [
            'submitForSettlement' => True
        ]
    ]);

    if($result->success || $result->transaction) {
        $app->redirect('/checkouts/' . $result->transaction->id);
    } else {
        $errorString = "";

        foreach($result->errors->deepAll() AS $error) {
            $errorString .= 'Error: ' . $error->code . ": " . $error->message . "\n";
        }

        $_SESSION["errors"] = $errorString;
        $app->redirect('/checkouts');
    }
});

$app->get('/checkouts/:transaction_id', function ($transaction_id) use ($app, $gateway) {
    $transaction = $gateway->transaction()->find($transaction_id);

    $transactionSuccessStatuses = [
        Braintree\Transaction::AUTHORIZED,
        Braintree\Transaction::AUTHORIZING,
        Braintree\Transaction::SETTLED,
        Braintree\Transaction::SETTLING,
        Braintree\Transaction::SETTLEMENT_CONFIRMED,
        Braintree\Transaction::SETTLEMENT_PENDING,
        Braintree\Transaction::SUBMITTED_FOR_SETTLEMENT
     ];

    if (in_array($transaction->status, $transactionSuccessStatuses)) {
        $header = "Sweet Success!";
        $icon = "success";
        $message = "Your test transaction has been successfully processed. See the Braintree API response and try again.";
    } else {
        $header = "Transaction Failed";
        $icon = "fail";
        $message = "Your test transaction has a status of " . $transaction->status . ". See the Braintree API response and try again.";
    }

    $app->render('checkouts/show.php', [
        'transaction' => $transaction,
        'header' => $header,
        'icon' => $icon,
        'message' => $message
    ]);
});

$app->run();
