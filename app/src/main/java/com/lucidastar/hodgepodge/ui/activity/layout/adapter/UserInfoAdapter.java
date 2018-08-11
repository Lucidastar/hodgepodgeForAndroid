package com.lucidastar.hodgepodge.ui.activity.layout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.layout.model.User;

import java.util.List;

/**
 * Created by qiuyouzone on 2018/7/27.
 */

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.MyUserViewHolder> {

    private List<User> mUsers;
    private Context mContext;
    private OnClickFabulousListener mOnClickFabulousListener;

    public UserInfoAdapter(List<User> users, Context context) {
        mUsers = users;
        mContext = context;
    }

    public void setOnClickFabulousListener(OnClickFabulousListener onClickFabulousListener) {
        mOnClickFabulousListener = onClickFabulousListener;
    }

    @Override
    public MyUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyUserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user_info,parent,false));
    }

    @Override
    public void onBindViewHolder(MyUserViewHolder holder, final int position) {
        User user = mUsers.get(position);
        holder.mTvUserName.setText(user.getName());
        holder.mTvUserAge.setText(user.getAge());
        holder.mTvFabulousNum.setText(user.getFabulous()+"次点赞");
        boolean fabulous = user.isFabulous();
        if (fabulous){
            holder.mIvFabulous.setImageResource(R.drawable.icon_zan_select);
        }else {
            holder.mIvFabulous.setImageResource(R.drawable.icon_zan_default);
        }

        holder.mIvFabulous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickFabulousListener != null){
                    mOnClickFabulousListener.clickFabulous(v,position);
                }
            }
        });
        Glide.with(mContext).load(user.getHead()).into(holder.mIvHead);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class MyUserViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvUserName;
        public TextView mTvUserAge;
        public TextView mTvFabulousNum;
        public ImageView mIvFabulous;
        public ImageView mIvHead;
        public MyUserViewHolder(View itemView) {
            super(itemView);
            mTvUserName = itemView.findViewById(R.id.tv_name);
            mTvUserAge = itemView.findViewById(R.id.tv_age);
            mTvFabulousNum = itemView.findViewById(R.id.tv_fabulous_num);
            mIvFabulous = itemView.findViewById(R.id.iv_fabulous);
            mIvHead = itemView.findViewById(R.id.iv_head);
        }
    }

    public interface OnClickFabulousListener{
        void clickFabulous(View view,int position);
    }
}
