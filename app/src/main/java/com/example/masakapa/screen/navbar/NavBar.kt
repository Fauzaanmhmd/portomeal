import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.masakapa.screen.navbar.BottomNavItem

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route


        items.forEach { item ->
            val isSelected = currentRoute == item.route
            val backgroundColor = if (isSelected) Color.Gray else Color.Transparent

            BottomNavigationItem(
                icon = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(backgroundColor)
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name
                        )
                    }
                },
                label = {
                    Text(
                        text = item.name,
                    )
                },
                selected = isSelected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Cyan,
                unselectedContentColor = Color.Gray
            )

        }
    }
}
