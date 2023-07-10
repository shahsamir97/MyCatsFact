package com.mdshahsamir.mycatsfact.ui.factslist

import com.mdshahsamir.mycatsfact.model.Animal

interface FactListItemActions {
    fun onClick(animal: Animal)
}