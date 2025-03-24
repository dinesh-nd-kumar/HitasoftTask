package com.example.hitasofttask.PostRepo

import com.example.hitasofttask.model.ChildItem
import com.example.hitasofttask.model.ParentItem

object values {

    val list = mutableListOf( ParentItem(
        1, "Category 1", false,
        childList = mutableListOf(
            ChildItem(1, "Item A"),
            ChildItem(2, "Item B"),
            ChildItem(3, "Item C")
            )
        ),
        ParentItem(
            2, "Category 2", false,
            childList = mutableListOf(
                ChildItem(1, "Item A"),
                ChildItem(2, "Item B"),
                ChildItem(3, "Item C")
            )
        ),
        ParentItem(
            3, "Category 3", false,
            childList = mutableListOf(
                ChildItem(1, "Item A"),
                ChildItem(2, "Item B"),
                ChildItem(3, "Item C")
            )
        ),
        ParentItem(
            4, "Category 4", false,
            childList = mutableListOf(
                ChildItem(1, "Item A"),
                ChildItem(2, "Item B"),
                ChildItem(3, "Item C")
            )
        ),

    )



}