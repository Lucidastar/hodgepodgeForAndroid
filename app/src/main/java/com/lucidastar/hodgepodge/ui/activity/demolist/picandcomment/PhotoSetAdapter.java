package com.lucidastar.hodgepodge.ui.activity.demolist.picandcomment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.PhotoView;
import com.lucidastar.hodgepodge.R;

import java.util.List;


/**
 * Created by long on 2016/9/28.
 * 图集 Adapter
 */
public class PhotoSetAdapter extends PagerAdapter {

    private List<String> mImgList;
    private Context mContext;
    private OnTapListener mTapListener;


    public PhotoSetAdapter(Context context, List<String> imgList) {
        this.mContext = context;
        this.mImgList = imgList;
    }


    @Override
    public int getCount() {
        return mImgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_pager, null, false);
        final PhotoView photo = (PhotoView) view.findViewById(R.id.iv_photo);

//        final TextView tvReload = (TextView) view.findViewById(R.id.tv_reload);


//        loadFitCenter(mContext, mImgList.get(position % mImgList.size()), photo, requestListener);
//        photo.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
//            @Override
//            public void onPhotoTap(View view, float x, float y) {
//                if (mTapListener != null) {
//                    mTapListener.onPhotoClick();
//                }
//            }
//        });


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setTapListener(OnTapListener listener) {
        mTapListener = listener;
    }

    public interface OnTapListener {
        void onPhotoClick();
    }

}
