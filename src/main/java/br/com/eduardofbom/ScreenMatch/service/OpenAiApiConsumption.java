package br.com.eduardofbom.ScreenMatch.service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionSystemMessageParam;
import com.openai.models.chat.completions.ChatCompletionUserMessageParam;
import io.github.cdimascio.dotenv.Dotenv;

public class OpenAiApiConsumption {

    private final static Dotenv dotenv = Dotenv.load();

    public static String getTranslation(String text) {
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(dotenv.get("OPENAI_API_KEY"))
                .build();

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model("gpt-4o-mini")
                .addMessage(ChatCompletionSystemMessageParam.builder()
                        .content("Você é um tradutor. Traduza o texto fornecido para português.")
                        .build())
                .addMessage(ChatCompletionUserMessageParam.builder()
                        .content(text)
                        .build())
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        ChatCompletion response = client.chat().completions().create(params);

        return response.choices().getFirst().message().content().orElse("ERROR: No content returned.");
    }

    static void main(String[] args) {
        String temp = OpenAiApiConsumption.getTranslation("I'm from Spain, because my mom was born in Valencia.");
        System.out.println(temp);
    }

}
