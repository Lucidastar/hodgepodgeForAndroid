package com.lucidastar.hodgepodge.ui.activity.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_data)
    Button mBtnData;
    @BindView(R.id.btn_showDialog)
    Button mBtnShowDialog;

    private BottomSheetDialog mBottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);
        mToolbar.setTitle("学习BottomSheet");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //https://github.com/soarcn/BottomSheet
        //http://www.jianshu.com/p/38af0cf77352
        //http://www.jianshu.com/p/0a7383e0ad0f
        //http://www.jianshu.com/p/8d43c222b551
    }

    @OnClick({R.id.btn_data, R.id.btn_showDialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_data:
                break;
            case R.id.btn_showDialog:
//                showShareDialog();
                showBottomSheetDialog();
                break;
        }
    }

    /**
     * share Dialog
     */
    private void showShareDialog(){
        if(mBottomSheetDialog == null){
            mBottomSheetDialog = new BottomSheetDialog(this);
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_share_dialog,null);
            mBottomSheetDialog.setContentView(view);
            mBottomSheetDialog.setCancelable(true);
            mBottomSheetDialog.setCanceledOnTouchOutside(true);
            // 解决下滑隐藏dialog 后，再次调用show 方法显示时，不能弹出Dialog
            View view1 = mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
            final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view1);
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        Log.i("BottomSheet","onStateChanged");
                        mBottomSheetDialog.dismiss();
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        }else{
            mBottomSheetDialog.show();
        }

    }

    private void showBottomSheetDialog(){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,null);

        handleList(view);

        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void handleList(View contentView){
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        MusicAdapter adapter = new MusicAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(mockData());
        adapter.notifyDataSetChanged();
    }

    /**
     * 模拟数据
     * @return
     */
    private List<MusicInfo> mockData(){
        List<MusicInfo> musicInfos = new ArrayList<>();
        MusicInfo musicInfo = new MusicInfo("哪里都是你","周杰伦");
        musicInfos.add(musicInfo);
        MusicInfo musicInfo1 = new MusicInfo("阳光宅男","周杰伦");
        musicInfos.add(musicInfo1);
        MusicInfo musicInfo2 = new MusicInfo("可爱女人","周杰伦");
        musicInfos.add(musicInfo2);
        MusicInfo musicInfo3 = new MusicInfo("火车叨位去","周杰伦");
        musicInfos.add(musicInfo3);
        MusicInfo musicInfo4 = new MusicInfo("我的地盘","周杰伦");
        musicInfos.add(musicInfo4);
        MusicInfo musicInfo5 = new MusicInfo("枫","周杰伦");
        musicInfos.add(musicInfo5);
        MusicInfo musicInfo6 = new MusicInfo("搁浅","周杰伦");
        musicInfos.add(musicInfo6);
        MusicInfo musicInfo7 = new MusicInfo("一路向北","周杰伦");
        musicInfos.add(musicInfo7);
        MusicInfo musicInfo8 = new MusicInfo("半岛铁盒","周杰伦");
        musicInfos.add(musicInfo8);
        MusicInfo musicInfo9 = new MusicInfo("霍元甲","周杰伦");
        musicInfos.add(musicInfo9);

        return musicInfos;
    }



    public static class MusicAdapter extends RecyclerView.Adapter{
        private List<MusicInfo> mData;

        public void setData(List<MusicInfo> data) {
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MusicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_music_item,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MusicViewHolder musicViewHolder = (MusicViewHolder) holder;

            MusicInfo musicInfo = mData.get(position);

            musicViewHolder.name.setText(musicInfo.name);

            musicViewHolder.singer.setText(musicInfo.singer);
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0:mData.size();
        }

        public static class MusicViewHolder extends RecyclerView.ViewHolder{
            public TextView name;
            public TextView singer;
            public MusicViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.music_name);
                singer = (TextView) itemView.findViewById(R.id.music_singer);
            }
        }
    }
}
