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
//878739583
$app->config([
    'templates.path' => 'templates',
]);

$app->get('/', function () use ($app) {
    //$app->redirect('/checkouts');
    echo "Iniciacion servicios con REST , SLIM y PHP";
});

$app->get('/token', function () use ($app, $gateway) {
    $clientToken = $gateway->clientToken()->generate();
    echo $clientToken;
});

$app->post('/TokenPorCliente', function () use ($app, $gateway) {
    $IdClienteBrain = $app->request->post('IdClienteBrain');
    $clientToken = $gateway->clientToken()->generate([
        "customerId" => $IdClienteBrain
    ]);

    $data["TokenBrain"] = $clientToken;
    echo  json_encode($data);

});


$app->post('/VerificarTarjeta', function () use ($app, $gateway) {
    $TokenBrain = $app->request->post('TokenBrain');
    $nonceFromTheClient = $app->request->post('nonceFromTheClient');
    $result = $gateway->paymentMethod()->create([
        'customerId' => $TokenBrain,
        'paymentMethodNonce' => $nonceFromTheClient,
        'options' => [
            'verifyCard' => true
        ]
    ]);

    echo json_encode($result);

});


$app->post('/CrearMetodoPago', function () use ($app, $gateway) {
    
    $IdClienteBrain = $app->request->post('IdClienteBrain');
    $NonceFromTheClient = $app->request->post('NonceFromTheClient');
    $result = $gateway->paymentMethod()->create([
      'customerId' => $IdClienteBrain,
      'paymentMethodNonce' => $NonceFromTheClient,
      'options' => [
        'verifyCard' => true,
        'verificationAmount' => '1.00',
      ]
    ]);

    echo json_encode($result);

});

$app->post('/EliminarMetodoPago', function () use ($app, $gateway) {

    $paymentMethodToken = $app->request->post('paymentMethodToken');
    try{
        $result = $gateway->paymentMethod()->delete($paymentMethodToken);

        $data["estatus"]  = $result->success;
       
    }
    catch (Braintree_Exception_NotFound $e) {

        $data["estatus"]  = false;
        $data["mensaje"] = $e->getMessage();
    }
   echo json_encode($data);
});


$app->post('/CrearTransaccion', function () use ($app, $gateway) {
    
    $paymentMethodToken = $app->request->post('paymentMethodToken');
    $amount = $app->request->post('amount');
    $result = $gateway->transaction()->sale(
    [
        'paymentMethodToken' => $paymentMethodToken,
        'amount' => $amount
    ]
    );

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


$app->post('/CrearCliente', function () use ($app, $gateway) {

    $data["Nombre"] = $app->request->post('IdClienteBrain');
    $data["Apellido"] = $app->request->post('Apellido');
    $data["Mail"] = $app->request->post('Mail');
    $data["Telefono"] = $app->request->post('Telefono');

    $result = $gateway->customer()->create([
        'firstName' => $data["Nombre"],
        'lastName' => $data["Apellido"],
        'company' => '',
        'email' => $data["Mail"],
        'phone' => $data["Telefono"],
        'fax' => '',
        'website' => ''
    ]);
    $dataResult["IdClienteBrain"] = $result->customer->id;
    echo json_encode($dataResult);

});

$app->post('/ObtenerCliente', function () use ($app, $gateway) {
    $customerId = $app->request->post('IdClienteBrain');
    $customer = $gateway->customer()->find($customerId);
    $dataResult["paymentMethods"] = $customer->paymentMethods;
    echo json_encode($dataResult);

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

?>