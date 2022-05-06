package id.ac.ubaya.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.todo.R
import id.ac.ubaya.todo.model.TdoDao
import id.ac.ubaya.todo.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.android.synthetic.main.todo_item_layout.*


class EditToDoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditToDoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        txtJudulToDo.text="Edit todo"
        btnAdd.text = "Save Changes"

        btnAdd.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGrupPriority.checkedRadioButtonId)
            viewModel.update(
                uuid,
                txtTitle.text.toString(),
                txtNotes.text.toString(),
                radio.tag.toString().toInt()
            )
            Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

    }

    private fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner){
            txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
            when (it.priority){
                1 -> radioLow.isChecked=true
                2-> radioMedium.isChecked = true
                else->radioHigh.isChecked=true
            }
        }
    }





}