package com.learning.netflixclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data to populate the RecyclerView with
        // data to populate the RecyclerView with
        val animalNames = listOf(
            "Horse", "Cow", "Camel", "Sheep", "Lion", "Elephant",
            "Tiger", "Giraffe", "Panda", "Dolphin", "Penguin",
            "Kangaroo", "Cheetah", "Gorilla", "Koala", "Polar Bear",
            "Zebra", "Octopus", "Owl", "Rhino", "Platypus",
            "Chimpanzee", "Fox")

        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvAnimals)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyRecyclerViewAdapter(this, animalNames)
        recyclerView.adapter = adapter

        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var previousScrollY = 0.0f
            var initialScrollViewPosition = nestedScrollView.y
            var shouldInitialize = true
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val isScrollingUp = dy > previousScrollY
                val isVisible = nestedScrollView.y >= 0.0f
                val beforeInitialPosition = nestedScrollView.y < initialScrollViewPosition

                if (isScrollingUp && isVisible) {
                    if (shouldInitialize) {
                        initialScrollViewPosition = nestedScrollView.y
                        shouldInitialize = false
                    }
                    nestedScrollView.y = nestedScrollView.y - dy
                } else if (!isScrollingUp) {
                    if (beforeInitialPosition) {
                        nestedScrollView.y = nestedScrollView.y - dy
                    } else {
                        if (!shouldInitialize) {
                            nestedScrollView.y = initialScrollViewPosition
                        }
                    }
                }
            }
        })
    }
}