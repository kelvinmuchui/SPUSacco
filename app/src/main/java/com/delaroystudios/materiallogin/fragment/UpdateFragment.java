package com.delaroystudios.materiallogin.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delaroystudios.materiallogin.Adpater.UpdateAdapter;
import com.delaroystudios.materiallogin.Adpater.UserAdpater;
import com.delaroystudios.materiallogin.R;
import com.delaroystudios.materiallogin.model.Member;
import com.delaroystudios.materiallogin.model.updates;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    RecyclerView recyclerView;

    UpdateAdapter adapterUsers;
    List<updates> usersList;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = view.findViewById(R.id.users_recyclerView);

        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        user = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        usersList = new ArrayList<>();
        getAllUpdates();
        return view;
    }

    private void getAllUpdates() {
        CollectionReference reRef = db.collection("Updates");

        reRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                usersList.clear();
                for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                    updates user = documentSnapshot.toObject(updates.class);

                    usersList.add((user));


                    adapterUsers = new UpdateAdapter(getActivity(), usersList);

                    recyclerView.setAdapter(adapterUsers);
                }


            }
        });
    }

}
