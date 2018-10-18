package com.qianmo.dragrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianmo.dragrecyclerview.bean.HomeUpdateBean;
import com.qianmo.dragrecyclerview.R;
import com.qianmo.dragrecyclerview.listener.UpdateCardItemListener;
import java.util.List;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.ViewHolder> {

  public List<HomeUpdateBean.CardBean> cardBeans;
  private Context mContext;
  private LayoutInflater mLiLayoutInflater;
  private ItemTouchHelper itemhelper;
  private UpdateCardItemListener mUpdateCardListener;

  public CardItemAdapter(List<HomeUpdateBean.CardBean> cardBeans, Context context,
      ItemTouchHelper helper, UpdateCardItemListener listener) {
    this.cardBeans = cardBeans;
    this.mContext = context;
    this.mLiLayoutInflater = LayoutInflater.from(mContext);
    this.itemhelper = helper;
    this.mUpdateCardListener = listener;
  }

  @Override
  public CardItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    CardItemAdapter.ViewHolder holder =
        new ViewHolder(mLiLayoutInflater.inflate(R.layout.item_categroy, parent, false));
    return holder;
  }

  @Override
  public void onBindViewHolder(final CardItemAdapter.ViewHolder holder, final int position) {
    final HomeUpdateBean.CardBean cardBean = cardBeans.get(position);
    holder.tv_title.setText(cardBean.getCard_name());
    holder.img_control.setImageResource(R.mipmap.ic_launcher);
    holder.img_control.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        itemhelper.startDrag(holder);
        return true;
      }
    });
    boolean item_select_state = cardBean.isIsopen();
    if(item_select_state){
      holder.img_Select.setImageResource(R.drawable.icon_service_switch_open);
    }else{
      holder.img_Select.setImageResource(R.drawable.icon_service_switch_close);
    }
    holder.img_Select.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        boolean curr_state = cardBeans.get(position).isIsopen();
        if (!curr_state) {
          cardBeans.get(position).setIsopen(true);
          ((ImageView)v).setImageResource(R.drawable.icon_service_switch_open);
        } else {
          cardBeans.get(position).setIsopen(false);
          ((ImageView)v).setImageResource(R.drawable.icon_service_switch_close);
        }
        mUpdateCardListener.updateCardState(cardBeans);
      }
    });
  }

  @Override
  public int getItemCount() {
    return cardBeans == null ? 0 : cardBeans.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_title;
    ImageView img_control;
    ImageView img_Select;

    public ViewHolder(View itemView) {
      super(itemView);
      tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
      img_control = (ImageView) itemView.findViewById(R.id.img_item);
      img_Select = (ImageView) itemView.findViewById(R.id.img_sleect);
    }
  }
}
