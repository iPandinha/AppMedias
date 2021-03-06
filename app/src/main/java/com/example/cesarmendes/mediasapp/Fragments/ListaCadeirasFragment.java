package com.example.cesarmendes.mediasapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cesarmendes.mediasapp.CadeirasAdapterInterface;
import com.example.cesarmendes.mediasapp.R;
import com.example.cesarmendes.mediasapp.models.Cadeira;

import java.util.ArrayList;

import adapter.CadeirasAdapter;
import database.databaseAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCadeirasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCadeirasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCadeirasFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, CadeirasAdapterInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;


    private OnFragmentInteractionListener mListener;

    public ListaCadeirasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCadeirasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCadeirasFragment newInstance(String param1, String param2) {
        ListaCadeirasFragment fragment = new ListaCadeirasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_cadeiras, container, false);
        listView = (ListView) view.findViewById(R.id.listaCadd);

        databaseAdapter dbAdapter = new databaseAdapter(getActivity());
        dbAdapter.openBD();
        ArrayList<Cadeira> listaCadd = dbAdapter.getCadeiras();
        dbAdapter.close();
        CadeirasAdapter caddAdapter= new CadeirasAdapter(getActivity(),listaCadd);
        caddAdapter.setOnItemClickListener(this);
        listView.setAdapter(caddAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.listaCadd:
                Cadeira cad = (Cadeira) parent.getItemAtPosition(position);
                mListener.listCaddRowClicked(cad, view);
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void onItemClick(Cadeira cadeira,View view) {
        mListener.listCaddRowClicked(cadeira, view);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void onFragmentInteraction(Uri uri);
        void listCaddRowClicked(Cadeira cad, View view);
    }
}
