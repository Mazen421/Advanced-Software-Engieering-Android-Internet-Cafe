package com.example.advancedsoftwareengineering.ui.transactions;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.Transaction;
import com.example.advancedsoftwareengineering.User;
import com.example.advancedsoftwareengineering.databinding.FragmentTransactionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TransactionFragment extends Fragment {

    private FragmentTransactionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TransactionViewModel transactionViewModel =
                new ViewModelProvider(this).get(TransactionViewModel.class);

        binding = FragmentTransactionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = requireActivity().getIntent();
        User actor= (User) intent.getSerializableExtra("Actor");

        List<Transaction> transactionshistory;
        List<String> listTransactions = new ArrayList<>();


        if(actor != null){
            transactionshistory = actor.getTransactionHistory();
            for(Transaction transaction : transactionshistory){
                listTransactions.add(transaction.getTimeAtCreation() +" Transaction: "+ transaction.getService() +" " + transaction.getState()+ " Price: "+ transaction.getPrice());
            }
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, listTransactions);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}