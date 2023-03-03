
package com.ag.bta.main.wavelayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.Nullable;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

import com.ag.bta.main.R;

public class WaveLayoutRecyclerView extends RecyclerView {

    public enum LayoutMangerType {
        LINEAR_VERTICAL, LINEAR_HORIZONTAL, GRID
    }

    private Adapter mActualAdapter;
    private WaveAdapter mWaveAdapter;

    private LayoutManager mWaveLayoutManager;
    private LayoutManager mActualLayoutManager;
    private LayoutMangerType mLayoutMangerType;

   private boolean mCanScroll;
    private int mLayoutReference;
    private int mGridCount;

    public WaveLayoutRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public WaveLayoutRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WaveLayoutRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mWaveAdapter = new WaveAdapter();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveLayoutRecyclerView, 0, 0);

        int mWaveAngle;
        int mWaveColor;
        int mWaveDuration;
        float mWaveMaskWidth;
        boolean isAnimationReversed;
        Drawable mWaveItemBackground;

        try {
            setDemoLayoutReference(a.getResourceId(R.styleable.WaveLayoutRecyclerView_rv_demolayout,
            		R.layout.wavelayout_dummy_view));
            setDemoChildCount(a.getInteger(R.styleable.WaveLayoutRecyclerView_rv_child_count, 10));
            setGridChildCount(a.getInteger(R.styleable.WaveLayoutRecyclerView_rv_grid_childcount, 2));

            final int value = a.getInteger(R.styleable.WaveLayoutRecyclerView_rv_layoutmanager_type, 0);
            switch (value) {
                case 0:
                    setDemoLayoutManager(LayoutMangerType.LINEAR_VERTICAL);
                    break;
                case 1:
                    setDemoLayoutManager(LayoutMangerType.LINEAR_HORIZONTAL);
                    break;
                case 2:
                    setDemoLayoutManager(LayoutMangerType.GRID);
                    break;
                default:
                    throw new IllegalArgumentException("This value for layout manager is not valid!");
            }

            mWaveAngle = a.getInteger(R.styleable.WaveLayoutRecyclerView_rv_wave_angle, 0);
            mWaveColor = a.getColor(R.styleable.WaveLayoutRecyclerView_rv_wave_color, getColor(R.color.textPrimaryGrid));
            mWaveItemBackground = a.getDrawable(R.styleable.WaveLayoutRecyclerView_rv_wave_item_background);
            mWaveDuration = a.getInteger(R.styleable.WaveLayoutRecyclerView_rv_wave_duration, 1500);
            mWaveMaskWidth = a.getFloat(R.styleable.WaveLayoutRecyclerView_rv_mask_width, 0.5f);
            isAnimationReversed = a.getBoolean(R.styleable.WaveLayoutRecyclerView_rv_reverse_animation, false);
        } finally {
            a.recycle();
        }

        mWaveAdapter.setWaveAngle(mWaveAngle);
        mWaveAdapter.setWaveColor(mWaveColor);
        mWaveAdapter.setWaveMaskWidth(mWaveMaskWidth);
        mWaveAdapter.setWaveItemBackground(mWaveItemBackground);
        mWaveAdapter.setWaveDuration(mWaveDuration);
        mWaveAdapter.setAnimationReversed(isAnimationReversed);

        showWaveAdapter();
    }

    /**
     * Specifies the number of child should exist in any row of the grid layout.
     *
     * @param count - count specifying the number of child.
     */
    public void setGridChildCount(int count) {
        mGridCount = count;
    }

    /**
     * Sets the layout manager for the Wave adapter.
     *
     * @param type layout manager reference
     */
    public void setDemoLayoutManager(LayoutMangerType type) {
        mLayoutMangerType = type;
    }

    /**
     * Sets the number of demo views should be shown in the Wave adapter.
     *
     * @param count - number of demo views should be shown.
     */
    public void setDemoChildCount(int count) {
        mWaveAdapter.setMinItemCount(count);
    }

    /**
     * Specifies the animation duration of Wave layout.
     *
     * @param duration - count specifying the duration of Wave in millisecond.
     */
    public void setDemoWaveDuration(int duration) {
        mWaveAdapter.setWaveDuration(duration);
    }

    /**
     * Specifies the the width of the Wave line.
     *
     * @param maskWidth - float specifying the width of Wave line. The value should be from 0 to less or equal to 1.
     *                  The default value is 0.5.
     */
    public void setDemoWaveMaskWidth(float maskWidth) {
        mWaveAdapter.setWaveMaskWidth(maskWidth);
    }

    /**
     * Sets the Wave adapter and shows the loading screen.
     */
    public void showWaveAdapter() {
        mCanScroll = false;

        if (mWaveLayoutManager == null) {
            initWaveManager();
        }

        setLayoutManager(mWaveLayoutManager);
        setAdapter(mWaveAdapter);
    }

    /**
     * Hides the Wave adapter
     */
    public void hideWaveAdapter() {
        mCanScroll = true;
        setLayoutManager(mActualLayoutManager);
        setAdapter(mActualAdapter);
    }

    public void setLayoutManager(LayoutManager manager) {
        if (manager == null) {
            mActualLayoutManager = null;
        } else if (manager != mWaveLayoutManager) {
            mActualLayoutManager = manager;
        }

        super.setLayoutManager(manager);
    }

    public void setAdapter(Adapter adapter) {
        if (adapter == null) {
            mActualAdapter = null;
        } else if (adapter != mWaveAdapter) {
            mActualAdapter = adapter;
        }

        super.setAdapter(adapter);
    }

    /**
     * Retrieves the actual adapter that contains the data set or null if no adapter is set.
     *
     * @return The actual adapter
     */
    public Adapter getActualAdapter() {
        return mActualAdapter;
    }

    public Adapter getWaveAdapter() {
        return mWaveAdapter;
    }


    public int getLayoutReference() {
        return mLayoutReference;
    }

    /**
     * Sets the demo layout reference
     *
     * @param mLayoutReference layout resource id of the layout which should be shown as demo.
     */
    public void setDemoLayoutReference(int mLayoutReference) {
        this.mLayoutReference = mLayoutReference;
        mWaveAdapter.setLayoutReference(getLayoutReference());
    }

    private void initWaveManager() {
        switch (mLayoutMangerType) {
            case LINEAR_VERTICAL:
                mWaveLayoutManager = new LinearLayoutManager(getContext());// {
/*                    public boolean canScrollVertically() {
                        return mCanScroll;
                    }
*/               // };
                 
                break;
            case LINEAR_HORIZONTAL:
                mWaveLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
                    /*public boolean canScrollHorizontally() {
                        return mCanScroll;
                    }*/
                };
                break;
            case GRID:
                mWaveLayoutManager = new GridLayoutManager(getContext(), mGridCount) {
                   /* public boolean canScrollVertically() {
                        return mCanScroll;
                    }*/
                };

                break;
        }
    }

    private int getColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getColor(id);
        } else {
            return getResources().getColor(id);
        }
    }
}
