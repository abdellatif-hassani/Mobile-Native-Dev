package ma.enset.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mMessages;

    public ChatAdapter(Context context, ArrayList<String> messages) {
        super(context, 0, messages);
        mContext = context;
        mMessages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_chat_message, parent, false);
        }

        String message = mMessages.get(position);

        TextView messageTextView = listItem.findViewById(R.id.messageTextView);
        messageTextView.setText(message);

        // Set alignment and color based on position
        if (position % 2 == 0) { // User's message
            messageTextView.setTextColor(mContext.getResources().getColor(android.R.color.holo_blue_dark));
            messageTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        } else { // Bot's message
            messageTextView.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
            messageTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }

        return listItem;
    }
}
