package com.example.nytimesdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.nytimesdemo.R;
import com.example.nytimesdemo.data.model.NewsData;
import com.example.nytimesdemo.ui.activity.MainActivity;
import com.example.nytimesdemo.ui.adapter.NewsListAdapter;
import com.example.nytimesdemo.ui.listener.OnRecyclerItemClickListener;
import com.example.nytimesdemo.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements OnRecyclerItemClickListener<NewsData> {
    // TODO: Rename parameter arguments, choose names that match
    private OnFragmentInteractionListener mListener;
    public static final String FRAGMENT_TAG = MainFragment.class.getSimpleName();

    private ProgressBar progressBar;
    private NewsListAdapter mAdapter;
    private List<NewsData> newsDataList =new ArrayList<>();
    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView =  view.findViewById(R.id.recyclerview);
        progressBar =view.findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter =new NewsListAdapter(getActivity(),newsDataList,this);
        recyclerView.setAdapter(mAdapter);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if(getActivity()!=null) {
            mainViewModel.setNetworkService((((MainActivity) getActivity()).networkService));
            if(NetworkUtils.isNetworkAvailable(getActivity())) {
                mainViewModel.callPopularNewsList();
            }else{
                Toast.makeText(getContext(),"internet not available",Toast.LENGTH_SHORT).show();
            }
        }

        mainViewModel.popularNewsList.observe(this, new Observer<List<NewsData>>() {
            @Override
            public void onChanged(List<NewsData> newsData) {
                progressBar.setVisibility(View.GONE);
                newsDataList=newsData;
                mAdapter.updateReceiptsList(newsDataList);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onItemClick(NewsData model, int itemPOsition) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("data",model);
         mListener.onFragmentInteraction(bundle);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Bundle bundle);
    }
}
