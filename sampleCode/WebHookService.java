package com.umesoft.service;

@Service
public class WebHookService {

    /**
     * Stripeから送信されるWebhookイベントを処理
     * @param payload Stripeから受信したイベントデータ
     */
    @PostMapping("/webhook")
    public void handleWebhook(JsonNode payload) {
        //Stripeから送信されたデータのイベントタイプを取得
        String eventType = payload.get("type").asText("default");

        if (eventType.equals("checkout.session.completed") ||
                eventType.equals("checkout.session.completed.async_payment_succeeded")) {
            //支払い成功時の処理

        } else if (eventType.equals("checkout.session.async_payment_failed")) {
            //支払い失敗時の処理

        }
    }
}