package edu.spsu.hackathon.android.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Item;

public class ItemArrayAdapter extends ArrayAdapter<Item> {

    List<Item> items;
    Context context;

    public ItemArrayAdapter(Context context, List<Item> items) {
        super(context, R.layout.item_list_item);

        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_list_item, null);
        }

        Item item = items.get(position);
        TextView itemText = (TextView) convertView.findViewById(R.id.item_name);
        itemText.setText(item.getName());

        TextView itemTypeText = (TextView) convertView.findViewById(R.id.item_type_text);
        itemTypeText.setText(item.getType().toString().toUpperCase());

        return convertView;
    }
}
