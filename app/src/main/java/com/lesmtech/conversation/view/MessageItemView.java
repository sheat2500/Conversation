package com.lesmtech.conversation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lesmtech.conversation.R;
import com.lesmtech.conversation.entity.Message;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Rindt
 * @version 0.1
 * @since 6/18/15
 */
public class MessageItemView extends FrameLayout {

    @InjectView(R.id.content)
    TextView content;

    private Message message;

    private Context context;

    public MessageItemView(Context context) {
        super(context);
    }

    public MessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MessageItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MessageItemView(Context context, Message message) {
        super(context);
        this.message = message;
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_message, null);
        addView(view);
        ButterKnife.inject(this);
        if (message != null) {
            setMessage(message);
        }
    }

    public void setMessage(Message message) {
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (message.isSender()) {
            content.setBackgroundResource(R.drawable.bubble_green);
            lp.gravity = Gravity.RIGHT;
            content.setGravity(Gravity.RIGHT);
        } else {
            content.setBackgroundResource(R.drawable.bubble_yellow);
            lp.gravity = Gravity.LEFT;
            content.setGravity(Gravity.LEFT);
        }
        content.setLayoutParams(lp);
        content.setText(message.getContent());
    }
}
