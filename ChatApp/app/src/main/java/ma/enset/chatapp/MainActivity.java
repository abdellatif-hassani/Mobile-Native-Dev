package ma.enset.chatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ChatService chatService;

    private ListView chatListView;
    private EditText messageInput;
    private Button sendButton;
    private ChatAdapter chatAdapter;
    private ArrayList<String> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.brainshop.ai")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatService = retrofit.create(ChatService.class);


        // Initialize views
        chatListView = findViewById(R.id.chatListView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);

        // Initialize chat messages
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, chatMessages);
        chatListView.setAdapter(chatAdapter);

        // Send button click listener
        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String message = messageInput.getText().toString().trim();
        if (!message.isEmpty()) {
            chatMessages.add(message);
            chatAdapter.notifyDataSetChanged();
            messageInput.setText("");
            chatListView.setSelection(chatAdapter.getCount() - 1);

            // Send message to API
            new SendMessageTask().execute(message);
        }
    }

    private class SendMessageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String resultMessage = "";
            try {
                String apiId = System.getProperty("api.id");
                String apiKey = System.getProperty("api.key");
                String uid = System.getProperty("api.uid");
                Call<ChatResponse> call = chatService.sendMessage(apiId, apiKey, uid, params[0]);
                Response<ChatResponse> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    resultMessage = response.body().getCnt();
                } else {
                    // Handle HTTP error
                    resultMessage = "HTTP error: " + response.code();
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle network error
                resultMessage = "Network error: " + e.getMessage();
            }
            return resultMessage;
        }

        @Override
        protected void onPostExecute(String resultMessage) {
            super.onPostExecute(resultMessage);
            chatMessages.add(resultMessage);
            chatAdapter.notifyDataSetChanged();
            chatListView.setSelection(chatAdapter.getCount() - 1);
        }
    }

}