package leaf.net.cn.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        listView = (ListView)findViewById(R.id.listView);
        //根据数据源
        List<Map<String,Object>> source = getDataSource();
        adapter = new CustomAdapter(this,source);
        listView.setAdapter(adapter);
    }

    /**
     * 获取图文类型数据源
     * @return
     */
    private List<Map<String,Object>> getDataSource(){
        List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>(){
            {
                add(new HashMap<String, Object>(){
                    {
                        put("title","爱奇艺");
                        put("data","110 MB");
                        put("img",R.mipmap.ic_iqiy);
                        put("describe","【全民追剧，不看才怪】");
                    }
                });
                add(new HashMap<String, Object>(){
                    {
                        put("title","微信");
                        put("data","108 MB");
                        put("img",R.mipmap.ic_wx);
                        put("describe","微信，是一个生活方式。");
                    }
                });
                add(new HashMap<String, Object>(){
                    {
                        put("title","习近平：中国坚决维护南海权益 愿同直接当事国谈判");
                        put("data","2016年04月28日 12:41");
                        put("img",R.mipmap.ic_news);
                        put("describe","中国一贯致力于维护南海地区和平稳定，坚定维护自身在南海的主权和相关权利，坚持通过同直接当事国友好协商谈判和平解决争议。中方愿同东盟国家一道努力，将南海建设成为和平之海、友谊之海、合作之海");
                    }
                });
                add(new HashMap<String, Object>(){
                    {
                        put("title","淘宝");
                        put("data","127 MB");
                        put("img",R.mipmap.ic_tb);
                        put("describe","用户方便快捷的生活消费入口");
                    }
                });
                add(new HashMap<String, Object>(){
                    {
                        put("title","爱奇艺1");
                        put("data","110 MB");
                        put("img",R.mipmap.ic_iqiy);
                        put("describe","【全民追剧，不看才怪】");
                    }
                });
                add(new HashMap<String, Object>(){
                    {
                        put("title","微信2");
                        put("data","108 MB");
                        put("img",R.mipmap.ic_wx);
                        put("describe","微信，是一个生活方式。");
                    }
                });
                add(new HashMap<String, Object>(){
                    {
                        put("title","习近平：中国坚决维护南海权益 愿同直接当事国谈判3");
                        put("data","2016年04月28日 12:41");
                        put("img",R.mipmap.ic_news);
                        put("describe","中国一贯致力于维护南海地区和平稳定，坚定维护自身在南海的主权和相关权利，坚持通过同直接当事国友好协商谈判和平解决争议。中方愿同东盟国家一道努力，将南海建设成为和平之海、友谊之海、合作之海");
                    }
                });
                add(new HashMap<String, Object>(){
                    {
                        put("title","淘宝4");
                        put("data","127 MB");
                        put("img",R.mipmap.ic_tb);
                        put("describe","用户方便快捷的生活消费入口");
                    }
                });
            }
        };
        return lst;
    }
}
