package leaf.net.cn.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

/**
 * Created by luobert on 2016/5/3.
 */
public class CustomAdapter extends BaseAdapter {

    //数据源
    private List<Map<String,Object>> source;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomAdapter(Context context,List<Map<String,Object>> data){
        layoutInflater = LayoutInflater.from(context);
        source = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println(" ------------------------getView ------------"+position);
        Map<String,Object> map = source.get(position);
        ImgView imgView = null;
        NewsView newsView = null;
        int type = getItemViewType(position);
        switch (type){
            case 1:
                if(convertView == null){
                    convertView = layoutInflater.inflate(R.layout.news_listview,null);
                    newsView = new NewsView(convertView);
                    newsView.initValue(map);
                    convertView.setTag(newsView);
                }else{
                    newsView = (NewsView) convertView.getTag();
                    newsView.initValue(map);
                }
                break;
            default:
                if(convertView == null){
                    convertView = layoutInflater.inflate(R.layout.img_listview,null);
                    imgView = new ImgView(convertView);
                    imgView.initValue(map);
                    convertView.setTag(imgView);
                }else{
                    imgView = (ImgView) convertView.getTag();
                    imgView.initValue(map);
                }
                break;
        }
        return convertView;
    }

    /**
     * 3的倍数去条目2的样式
     */
    @Override
    public int getItemViewType(int position) {
        int type =  (position != 0 && position % 3 == 0)?1:0;
        return type;
    }


    /**
     * 有几种条目样式
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }


    class BaseViews{
        TextView title;
        TextView describe;
        ImageView img;
        TextView data;
        public void initValue( Map<String,Object> map){
            title.setText(map.get("title").toString());
            describe.setText(map.get("describe").toString());
            data.setText(map.get("data").toString());
            img.setImageResource(Integer.parseInt(map.get("img").toString()));
        }
    }

    class ImgView extends BaseViews{
        public ImgView(View convertView) {
            title = (TextView)convertView.findViewById(R.id.title);
            describe = (TextView)convertView.findViewById(R.id.describe);
            img = (ImageView) convertView.findViewById(R.id.imgs);
            data = (TextView)convertView.findViewById(R.id.size);
        }
    }
    class NewsView extends BaseViews{
        public NewsView(View convertView) {
            title = (TextView)convertView.findViewById(R.id.newsTitle);
            describe = (TextView)convertView.findViewById(R.id.newsDescribe);
            img = (ImageView) convertView.findViewById(R.id.newsImg);
            data = (TextView)convertView.findViewById(R.id.newsDate);
        }
    }
}
