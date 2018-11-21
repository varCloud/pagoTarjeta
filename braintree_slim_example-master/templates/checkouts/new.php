<?php $clientToken = $this->data["client_token"]; ?>
<html>
<?php require_once("includes/head.php"); ?>
<body>
<?php require_once("includes/header.php"); ?>

    <div class="wrapper">
        <div class="checkout container">

            <header>
                <h1>Hi, <br>Let's test a transaction</h1>
                <p>
                    Make a test payment with Braintree using PayPal or a card
                </p>
            </header>

            <form method="post" id="payment-form" action="/checkouts">
                <section>
                    <label for="amount">
                        <span class="input-label">Amount</span>
                        <div class="input-wrapper amount-wrapper">
                            <input id="amount" name="amount" type="tel" min="1" placeholder="Amount" value="10">
                        </div>
                    </label>

                    <div class="bt-drop-in-wrapper">
                        <div id="bt-dropin"></div>
                    </div>
                </section>

                <input id="nonce" name="payment_method_nonce" type="hidden" />
                <button class="button" type="submit"><span>Test Transaction</span></button>
            </form>
        </div>
    </div>

    <script src="https://js.braintreegateway.com/web/dropin/1.14.1/js/dropin.min.js"></script>
    <script>
      var form = document.querySelector('#payment-form');
      var client_token = "<?php echo($client_token); ?>";

      braintree.dropin.create({
        authorization: client_token,
        container: '#bt-dropin',
        paypal: {
          flow: 'vault'
        }
      }, function (createErr, instance) {
        form.addEventListener('submit', function (event) {
          event.preventDefault();

          instance.requestPaymentMethod(function (err, payload) {
            if (err) {
              console.log('Error', err);
              return;
            }

            // Add the nonce to the form and submit
            document.querySelector('#nonce').value = payload.nonce;
            form.submit();
          });
        });
      });
    </script>
    <script src="/static/javascript/demo.js"></script>
</body>
</html>