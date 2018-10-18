package com.qianmo.dragrecyclerview.listener;

/**
 * Created by Administrator on 2018/10/17.
 */
import com.qianmo.dragrecyclerview.bean.HomeUpdateBean;
import java.util.List;

public interface UpdateCardItemListener{
  void  updateCardState(List<HomeUpdateBean.CardBean> list);
}