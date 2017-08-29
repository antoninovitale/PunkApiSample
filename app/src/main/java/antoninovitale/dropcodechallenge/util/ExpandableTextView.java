package antoninovitale.dropcodechallenge.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import antoninovitale.dropcodechallenge.R;

/**
 * Custom TextView with feature to collapse / expand text based on a maximum number of lines.
 */
public class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {

    private final int DEFAULT_MAX_LINES = 0;

    private final String DEFAULT_VIEW_MORE_TEXT = "View More";

    private final String DEFAULT_VIEW_LESS_TEXT = "View Less";

    private final String DEFAULT_ELLIPSIZE_TEXT = "...";

    private final String mViewMoreText;

    private final String mViewLessText;

    private final String mEllipsizeText = DEFAULT_ELLIPSIZE_TEXT;

    boolean textInternallyChanged = false;

    private int mMaxLines;

    private boolean mCollapsed = false;

    private String mOriginalText = "";

    final OnGlobalLayoutListener onGlobalLayoutListener = new OnGlobalLayoutListener() {

        @SuppressWarnings("deprecation")
        @Override
        public void onGlobalLayout() {
            ViewTreeObserver obs = getViewTreeObserver();
            obs.removeGlobalOnLayoutListener(this);

            if (!mCollapsed && mMaxLines > 0 && getLineCount() > mMaxLines) {
                mCollapsed = true;

                int lineEndIndex = getLayout().getLineEnd(mMaxLines - 1);
                String text = String.format("%s%s %s", getText().subSequence(0, lineEndIndex -
                                mViewMoreText.length() - mEllipsizeText.length() + 1),
                        mEllipsizeText,
                        mViewMoreText);
                setMovementMethod(LinkMovementMethod.getInstance());
                changeText(addClickablePartTextViewResizable(text, mViewMoreText));
            } else if (mCollapsed && mMaxLines > 0 && getLineCount() > mMaxLines) {
                mCollapsed = false;

                int lineEndIndex = getLayout().getLineEnd(getLayout().getLineCount() - 1);
                String text = String.format("%s %s", getText().subSequence(0, lineEndIndex),
                        mViewLessText);
                setMovementMethod(LinkMovementMethod.getInstance());
                changeText(addClickablePartTextViewResizable(text, mViewLessText));
            }
        }
    };

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable
                .ExpandableTextView, 0, 0);

        try {
            mMaxLines = a.getInt(R.styleable.ExpandableTextView_maxLinesToCollapse,
                    DEFAULT_MAX_LINES);

            String viewMore = a.getString(R.styleable.ExpandableTextView_viewMoreText);
            String viewLess = a.getString(R.styleable.ExpandableTextView_viewLessText);

            mViewMoreText = TextUtils.isEmpty(viewMore) ? DEFAULT_VIEW_MORE_TEXT : viewMore;
            mViewLessText = TextUtils.isEmpty(viewLess) ? DEFAULT_VIEW_LESS_TEXT : viewLess;
        } finally {
            a.recycle();
        }

        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnGlobalLayoutListener(onGlobalLayoutListener);

        mOriginalText = getText().toString();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (textInternallyChanged) {
            textInternallyChanged = false;
        } else {
            mOriginalText = text.toString();
        }
    }

    private void changeText(SpannableStringBuilder text) {
        textInternallyChanged = true;
        setText(text);
    }

    private void changeText(CharSequence text, BufferType type) {
        textInternallyChanged = true;
        setText(text, type);
    }

    private SpannableStringBuilder addClickablePartTextViewResizable(final String strSpanned,
                                                                     final String spanableText) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (strSpanned.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                    setLayoutParams(getLayoutParams());
                    changeText(mOriginalText, BufferType.SPANNABLE);
                    invalidate();

                    ViewTreeObserver vto = getViewTreeObserver();
                    vto.addOnGlobalLayoutListener(onGlobalLayoutListener);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(ContextCompat.getColor(getContext(), R.color.blue));
                    ds.setUnderlineText(false);
                }
            }, strSpanned.indexOf(spanableText), strSpanned.indexOf(spanableText) + spanableText
                    .length(), 0);
        }
        return ssb;

    }

    public void setMaxLinesToCollapse(int maxlines) {
        mMaxLines = maxlines;
    }
}
