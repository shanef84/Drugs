package forbes.drugs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MedicinesAdapter extends ArrayAdapter<Medicines> {
    public MedicinesAdapter(Context context, int resource, List<Medicines> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_medicine, parent, false);
        }

        TextView medicineTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView paTextView = (TextView) convertView.findViewById(R.id.paTextView);

        Medicines medicines = getItem(position);

        medicineTextView.setVisibility(View.VISIBLE);
        paTextView.setVisibility(View.VISIBLE);
        medicineTextView.setText(medicines.getProductName());
        paTextView.setText(medicines.getLicenseNumber());

        return convertView;
    }
}


