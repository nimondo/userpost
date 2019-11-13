package com.example.userpost.ui.user

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.userpost.R
import com.example.userpost.base.BaseViewModel
import com.example.userpost.model.User
import com.example.userpost.model.UserDao
import com.example.userpost.network.UserApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserListViewModel(private val userDao: UserDao): BaseViewModel(){
    @Inject
    lateinit var userApi: UserApi
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadUsers() }
    val userListAdapter: UserListAdapter = UserListAdapter()


    init{
        loadUsers()
    }

    private fun loadUsers(){
        subscription = Observable.fromCallable { userDao.all }
            .concatMap {
                    dbUserList ->
                if(dbUserList.isEmpty())
                    userApi.getUsers().concatMap {
                            apiUserList -> userDao.insertAll(*apiUserList.toTypedArray())
                        Observable.just(apiUserList)
                    }
                else
                    Observable.just(dbUserList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveUserListStart() }
            .doOnTerminate { onRetrieveUserListFinish() }
            .subscribe(
                { result ->onRetrieveUserListSuccess(result) },
                { onRetrieveUserListError() }
            )
    }

    private fun onRetrieveUserListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveUserListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveUserListSuccess(userList:List<User>){
        userListAdapter.updateUserList(userList)
    }
    private fun onRetrieveUserListError(){
        errorMessage.value = R.string.post_error
    }
    fun onUsetListClicked(id: Int) {
//        _navigateToSleepDetail.value = id
    }
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}