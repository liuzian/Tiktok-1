package com.bytedance.tiktok.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.utils.AutoLinkHerfManager;
import com.bytedance.tiktok.utils.OnVideoControllerListener;
import com.bytedance.tiktok.utils.autolinktextview.AutoLinkTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.view.animation.Animation.INFINITE;

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
public class ControllerView extends RelativeLayout implements View.OnClickListener {
    @BindView(R.id.tv_content)
    AutoLinkTextView autoLinkTextView;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.lottie_anim)
    LottieAnimationView animationView;
    @BindView(R.id.rl_like)
    RelativeLayout rlLike;
    @BindView(R.id.iv_comment)
    IconFontTextView ivComment;
    @BindView(R.id.iv_share)
    IconFontTextView ivShare;
    @BindView(R.id.iv_record)
    ImageView ivRecord;
    @BindView(R.id.rl_record)
    RelativeLayout rlRecord;
//    private ImageView ivHead;
//    private IconFontTextView ivLike;
//    private IconFontTextView ivComment;
//    private IconFontTextView ivShare;
//    private AutoLinkTextView autoLinkTextView;
//    private RelativeLayout ivRecord, rlLike;
    private OnVideoControllerListener listener;

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_controller, this);
        ButterKnife.bind(this, rootView);

        ivHead.setOnClickListener(this);
        ivComment.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        rlLike.setOnClickListener(this);

        AutoLinkHerfManager.setContent("只有 #允儿 的脸我才敢拉这么近 @肖战 @王一博 来呀来呀", autoLinkTextView);

        setRotateAnim();
    }

    public void setListener(OnVideoControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.iv_head:
                listener.onHeadClick();
                break;
            case R.id.rl_like:
                listener.onLikeClick();

                animationView.setAnimation("like.json");
                animationView.playAnimation();
                break;
            case R.id.iv_comment:
                listener.onCommentClick();
                break;
            case R.id.iv_share:
                listener.onShareClick();
                break;
        }
    }

    /**
     * 循环旋转动画
     */
    private void setRotateAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(INFINITE);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ivRecord.startAnimation(rotateAnimation);
    }
}
