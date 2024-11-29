package com.umesoft.service;

@Service
public class PaymentService {

    /**
     * 支払い画面を生成
     * @param payRequest 支払いリクエスト情報
     * @return 支払い画面セッションの情報（IDを含む）
     * @throws Exception Stripe API呼び出し時の例外
     */
    @PostMapping("/create-payment")
    public Map<String, String> createPay(PayRequest payRequest) throws Exception {

        //Stripeのシークレットキーを設定
        Stripe.apiKey = "sk_test_yourSecretKey";

        //支払いセッションのパラメータを構築
        SessionCreateParams.Builder builder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                //支払い成功時のリダイレクトURLを設定
                .setSuccessUrl("https://yourwebsite.com/orders/" + orderNo)
                //支払いキャンセル時のリダイレクトURLを設定
                .setCancelUrl("https://yourwebsite.com/orders/" + orderNo)
                //カスタムデータに注文番号を追加（Webhookで利用可能）
                .putMetadata("orderNo", orderNo);

        //Stripeセッションを作成
        Session session = Session.create(builder.build());
        return responseData;
    }

    /**
     * 注文の返金を処理
     * @param orderNo 返金対象の注文番号
     * @return 返金処理が成功したかどうか
     * @throws StripeException Stripe API呼び出し時の例外
     */
    @PutMapping("/orders/{orderNo}/cancel")
    public boolean refund(@PathVariable("orderNo") String orderNo) throws StripeException {
        //Stripeのシークレットキーを設定
        Stripe.apiKey = "sk_test_yourSecretKey";

        //返金パラメータを構築
        RefundCreateParams params = RefundCreateParams.builder()
                .setPaymentIntent(paymentId)
                .build();

        //返金を実行
        Refund refund = Refund.create(params);

        //返金処理が正常終了
        return true;
    }
}