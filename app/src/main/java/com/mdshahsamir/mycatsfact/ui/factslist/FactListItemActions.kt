package com.mdshahsamir.mycatsfact.ui.factslist

import android.view.View
import com.mdshahsamir.mycatsfact.model.Animal

interface FactListItemActions {
    fun onClick(animal: Animal, view: View)
}
