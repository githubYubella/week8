package id.ac.ubaya.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.todo.R
import id.ac.ubaya.todo.viewmodel.ListToDoViewModel
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment() {
    private lateinit var viewModel: ListToDoViewModel
    private val todoListAdapter  = TodoListAdapter(arrayListOf()) {
        viewModel.clearTask(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListToDoViewModel::class.java)
        viewModel.refresh()
        recViewToDo.layoutManager = LinearLayoutManager(context)
        recViewToDo.adapter = todoListAdapter

        fabAddToDo.setOnClickListener {
            val action=TodoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()



    }
    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner) {
        todoListAdapter.updateToDoList(it)
        txtEmpty.visibility = if(it.isEmpty())  View.VISIBLE else View.GONE
        }
    }



}