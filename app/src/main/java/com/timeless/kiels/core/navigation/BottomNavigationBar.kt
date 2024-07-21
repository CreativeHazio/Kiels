package com.timeless.kiels.core.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.timeless.kiels.ui.theme.DividerGray
import com.timeless.kiels.ui.theme.Gray
import com.timeless.kiels.ui.theme.Transparent
import com.timeless.kiels.ui.theme.Yellow

@Composable
fun BottomNavigationBar(
    items : List<BottomNavItem>,
    navController: NavController
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    Surface(
       modifier = Modifier.fillMaxWidth(),
       shadowElevation = 8.dp,
       color = MaterialTheme.colorScheme.primary
    ) {

        Divider(
            color = if (isSystemInDarkTheme()) DividerGray else Gray,
            thickness = 0.3.dp,
            modifier = Modifier.fillMaxWidth()
        )

        BottomAppBar (
            modifier = Modifier.height(60.dp),
            containerColor = Transparent,
        ) {
            items.forEach {
                val isSelected = it.route == currentRoute
                NavigationBarItem(
                    modifier = Modifier.padding(10.dp),
                    selected = isSelected,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.secondary,
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Gray,
                        unselectedTextColor = Gray
                    ),
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.name
                            )
                            if (isSelected) {
                                Spacer(modifier = Modifier.size(3.dp))
                                Text(
                                    text = it.name,
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                )
            }
        }


    }



}