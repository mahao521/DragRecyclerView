package com.qianmo.dragrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianmo.dragrecyclerview.utils.DetailItemTouchHelper;
import com.qianmo.dragrecyclerview.utils.DividerItemDecoration;
import com.qianmo.dragrecyclerview.bean.HomeUpdateBean;
import com.qianmo.dragrecyclerview.R;
import com.qianmo.dragrecyclerview.SwipeRecyclerView;
import com.qianmo.dragrecyclerview.listener.UpdateCardItemListener;
import com.qianmo.dragrecyclerview.listener.UpdateCategroyListener;
import java.util.List;

public class CategroyItemAdapter extends RecyclerView.Adapter<CategroyItemAdapter.ViewHolder> {

  private static final String TAG = "CategroyItemAdapter";
  private List<HomeUpdateBean> mhomeUpdateBeans;
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private CardItemAdapter cardItemAdapter;
  private ItemTouchHelper mItemTouchHelper;
  private UpdateCategroyListener mCategroyListener;

  public CategroyItemAdapter(final List<HomeUpdateBean> datas, Context context, UpdateCategroyListener listener) {
    this.mhomeUpdateBeans = datas;
    this.mContext = context;
    this.mLayoutInflater = LayoutInflater.from(mContext);
    this.mCategroyListener = listener;
  }

  @Override
  public CategroyItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

   CategroyItemAdapter.ViewHolder holder = new ViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
    Log.d(TAG, "onCreateViewHolder: " + holder.toString());
    return holder;
  }

  @Override
  public void onBindViewHolder(final CategroyItemAdapter.ViewHolder holder, final int position) {
    HomeUpdateBean cateGroyBean = mhomeUpdateBeans.get(position);
    holder.txt_card_name.setText(cateGroyBean.getCategroy_title());
    holder.img_card_icon.setImageResource(cateGroyBean.getCategroy_image());
    Log.d(TAG, "onBindViewHolder: " + position);
    mItemTouchHelper = new ItemTouchHelper(new DetailItemTouchHelper(cateGroyBean.getCardList()));
    holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    holder.mRecyclerView.addItemDecoration(
        new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
    cardItemAdapter = new CardItemAdapter(cateGroyBean.getCardList(), mContext, mItemTouchHelper,
        new UpdateCardItemListener() {
          @Override public void updateCardState(List<HomeUpdateBean.CardBean> list) {
            mhomeUpdateBeans.get(position).setCardList(list);
            mCategroyListener.updateCategroyData(mhomeUpdateBeans);
          }
        });
    holder.mRecyclerView.setAdapter(cardItemAdapter);
    mItemTouchHelper.attachToRecyclerView(holder.mRecyclerView);
  }

  @Override
  public int getItemCount() {
    return mhomeUpdateBeans == null ? 0 : mhomeUpdateBeans.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView txt_card_name;
    public ImageView img_card_icon;
    public SwipeRecyclerView mRecyclerView;

    public ViewHolder(View itemView) {
      super(itemView);
      txt_card_name = (TextView) itemView.findViewById(R.id.tv_title);
      img_card_icon = (ImageView) itemView.findViewById(R.id.img);
      mRecyclerView = (SwipeRecyclerView) itemView.findViewById(R.id.rv_item_recycler);
    }
  }
}
