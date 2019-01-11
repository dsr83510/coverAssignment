package com.sdurga.assignments.autocompleteDemo.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sdurga.assignments.com.autocompletedemo.R;

public class InsuranceListAdapter extends BaseAdapter implements Filterable {

    private static final String TAG = InsuranceListAdapter.class.getSimpleName();
    private List<String> insuranceCarriersList;
    private List<String> filteredList;
    private PrefixFilter filter = new PrefixFilter();

    private class PrefixFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            if (constraint == null || constraint.length() == 0) {
                results.values = insuranceCarriersList;
                results.count = insuranceCarriersList.size();
            } else {
                ArrayList<String> output = new ArrayList<>();
                for (String name : insuranceCarriersList) {
                    if (name.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        output.add(name);
                    }
                }
                results.values = output;
                results.count = output.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                filteredList = (List<String>)results.values;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private LayoutInflater layoutInflater;

    public InsuranceListAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        if (filteredList != null) {
            return filteredList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (filteredList != null) {
            return filteredList.get(position);
        }
        Log.e(TAG,"getItem() returned a null object");
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String insuranceCarrier = filteredList.get(position);
        ViewHolder viewHolder =  null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.insurance_carriers_predictions_list_row,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.insurance_carrier_name);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(insuranceCarrier != null){
            viewHolder.textView.setText(insuranceCarrier);
        }
        return convertView;
    }

    public void setInsuranceCarriersList(List<String> insuranceCarriersList) {
        this.insuranceCarriersList = insuranceCarriersList;
        this.filteredList = insuranceCarriersList;
    }

    //region VIEW HOLDER CLASS

    private static class ViewHolder {
        TextView textView;
    }

    // endregion
}
