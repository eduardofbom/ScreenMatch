package br.com.eduardofbom.ScreenMatch.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import io.github.cdimascio.dotenv.Dotenv;

public class GeminiApiConsumption {

    private final static Dotenv dotenv = Dotenv.load();

    public static String getTranslate(String text) {
        String API_KEY = dotenv.get("GOOGLE_API_KEY");
        if(API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException("The environment variable 'GOGLE_API_KEY' was not found.");
        }

        Client client = Client.builder()
                .apiKey(API_KEY)
                .build();

        String prompt = String.format("""
                Você é um tradutor. Traduza o texto a seguir para o português do Brasil.
                Responda APENAS com a tradução, sem aspas, explicações ou notas.
                Texto: %s""", text);

        GenerateContentResponse response = client.models.generateContent(
                "gemini-3.5-flash",
                prompt,
                null  // configuração padrão (temperature, maxTokens)
        );

        return response.text().trim();
    }

}
