package com.qianmo.dragrecyclerview;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qianmo.dragrecyclerview.adapter.CategroyItemAdapter;
import com.qianmo.dragrecyclerview.bean.HomeUpdateBean;
import com.qianmo.dragrecyclerview.listener.OnRecyclerItemClickListener;
import com.qianmo.dragrecyclerview.listener.UpdateCategroyListener;
import com.qianmo.dragrecyclerview.utils.*;
import java.util.ArrayList;
import java.util.List;

import static com.qianmo.dragrecyclerview.R.id.recyclerView;
public class MainActivity extends AppCompatActivity implements UpdateCategroyListener {

    private static final String TAG = "MainActivity";
    private Context mContext = MainActivity.this;
    private SwipeRecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private CategroyItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = (SwipeRecyclerView) findViewById(recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new com.qianmo.dragrecyclerview.utils.DividerItemDecoration(this, com.qianmo.dragrecyclerview.utils.DividerItemDecoration.VERTICAL_LIST));
        itemAdapter = new CategroyItemAdapter(getUpdateBean(), mContext,this);
        mRecyclerView.setAdapter(itemAdapter);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Toast.makeText(mContext, "111", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                if(vh instanceof  CategroyItemAdapter.ViewHolder){
                    final CategroyItemAdapter.ViewHolder viewHolder = (CategroyItemAdapter.ViewHolder) vh;
                    viewHolder.img_card_icon.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            mItemTouchHelper.startDrag(viewHolder);
                            //获取振动service;
                            Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                            vib.vibrate(70);
                            return true;
                        }
                    });
                }
            }
        });
        mItemTouchHelper = new ItemTouchHelper(new DetailItemTouchHelper(getUpdateBean()));
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    public List<HomeUpdateBean> getUpdateBean(){
        List<HomeUpdateBean> updateList = new ArrayList<>();
        HomeUpdateBean bean = new HomeUpdateBean();
        bean.setCategroy_title("功能卡片");
        bean.setCategroy_image(R.mipmap.ic_category_0);
        List<HomeUpdateBean.CardBean> cardBeans = new ArrayList<>();
        HomeUpdateBean.CardBean cardBean = bean.new CardBean();
        cardBean.setCard_name("我的设备");
        cardBean.setIsopen(false);
        cardBeans.add(cardBean);
        bean.setCardList(cardBeans);
        updateList.add(bean);

        HomeUpdateBean bean1 = new HomeUpdateBean();
        bean1.setCategroy_title("数据卡片");
        bean1.setCategroy_image(R.mipmap.ic_category_1);
        List<HomeUpdateBean.CardBean> cardBeans1 = new ArrayList<>();
        HomeUpdateBean.CardBean cardBean1 = bean1.new CardBean();
        cardBean1.setCard_name("体重");
        cardBean1.setIsopen(false);
        cardBeans1.add(cardBean1);
        HomeUpdateBean.CardBean cardBean2 = bean1.new CardBean();
        cardBean2.setCard_name("脂肪");
        cardBean2.setIsopen(false);
        cardBeans1.add(cardBean2);
        HomeUpdateBean.CardBean cardBean3 = bean1.new CardBean();
        cardBean3.setCard_name("肌肉");
        cardBean3.setIsopen(false);
        cardBeans1.add(cardBean3);
        bean1.setCardList(cardBeans1);
        updateList.add(bean1);

        return updateList;
    }

    @Override public void updateCategroyData(List<HomeUpdateBean> homeUpdateBeans) {
        Log.d(TAG, "updateCategroyData: " + homeUpdateBeans.toString());
    }
}
