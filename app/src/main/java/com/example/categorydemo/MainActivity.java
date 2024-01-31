package com.example.categorydemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView oneRecycler;
    private RecyclerView twoRecycler;

    private OneTypeAdapter oneTypeAdapter;
    private TwoTypeAdapter twoTypeAdapter;
    private List<String> oneList = new ArrayList<>();

    private List<String> twoList = new ArrayList<>();
    private int currentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        oneRecycler = findViewById(R.id.one);
        twoRecycler = findViewById(R.id.two);
        oneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        twoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        oneTypeAdapter=new OneTypeAdapter(this, oneList);
        twoTypeAdapter=new TwoTypeAdapter(this, oneList,twoList);

        oneRecycler.setAdapter(oneTypeAdapter);
        twoRecycler.setAdapter(twoTypeAdapter);

        oneTypeAdapter.setOnItemClickListener(new OneTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                LinearLayoutManager twoRecyclerLayoutManager = ((LinearLayoutManager) twoRecycler.getLayoutManager());
                if (twoRecyclerLayoutManager!=null){
                    /**
                     这里有个问题，可能因为字号等问题，左边最后n个条目可能出现点击了但是颜色没渲染上的问题，不知道啥原因
                     然后我就是最后n个点击事件手动处理，比如我这就是最后3个点击有问题
                     */
                    if (position>= oneList.size()-3){
                        oneTypeAdapter.selectedPosition=position;
                        oneTypeAdapter.notifyDataSetChanged();
                        twoRecyclerLayoutManager.scrollToPositionWithOffset(position,0);
                    }
                    else {
                        twoRecyclerLayoutManager.scrollToPositionWithOffset(position,0);
                    }
                }
            }
        });


        twoRecycler.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LinearLayoutManager twoRecyclerLayoutManager = (LinearLayoutManager) twoRecycler.getLayoutManager();
                LinearLayoutManager oneRecyclerLayoutManager = ((LinearLayoutManager) oneRecycler.getLayoutManager());

                if (twoRecyclerLayoutManager!=null&&oneRecyclerLayoutManager!=null){
                    /**
                     获取第一个可见的item的position
                     */
                    currentPosition = twoRecyclerLayoutManager.findFirstVisibleItemPosition();
                    Log.e("TAG", "onScrollChange: "+currentPosition );
                    /**
                     这地方需要进行判断，如果右边的Recycle在移动的时候，左边的RecyclerView也是需要进行移动的
                     左边的recyclerview有可能会不可见，这时候，我们必须去判断一下，左边最后的一个item是不是
                     小于右边滑动的位置，或左边第一个item是不是大于右边滑动的位置
                     */
                    if (oneRecyclerLayoutManager.findFirstVisibleItemPosition() > currentPosition) {
                        oneRecyclerLayoutManager.scrollToPositionWithOffset(currentPosition, 0);
                    } else if (oneRecyclerLayoutManager.findFirstVisibleItemPosition() < currentPosition) {
                        oneRecyclerLayoutManager.scrollToPositionWithOffset(currentPosition, 0);
                    }

                    /**
                     判断右边是否滑动到最后一个item，是的话，也将左边移动到最后一个item
                     canScrollVertically(1)表示是否能向上滚动，false表示已经滚动到底部
                     */
                    if (!twoRecycler.canScrollVertically(1)) {
                        currentPosition = oneList.size() - 1;
                    }
                    oneTypeAdapter.selectedPosition=currentPosition;
                    oneTypeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            oneList.add("一级类型"+i);
        }
        for (int i=0;i<10;i++){
            twoList.add("二级类型"+i);
        }
    }
}