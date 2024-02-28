package com.hh.testapponlineshop

import com.hh.testapponlineshop.domain.models.UserDomain

class Session
{
    private var currentUser: UserDomain? = null

    fun setCurrentUser(user: UserDomain)
    {
        currentUser = user
    }

    fun getCurrentUser(): UserDomain
    {
        return currentUser!!
    }
}