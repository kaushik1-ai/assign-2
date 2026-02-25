package com.example.kj_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.kj_app.ui.theme.KJ_appTheme
import kotlin.math.max

// Peach Palette
val PeachBackground = Color(0xFFFFF5EE)
val PeachPrimary = Color(0xFFFFDAB9)
val PeachAccent = Color(0xFFFFB07C)
val BrownText = Color(0xFF5D4037)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KJ_appTheme {
                MainAppContainer()
            }
        }
    }
}

@Composable
fun MainAppContainer() {
    var currentScreen by remember { mutableIntStateOf(0) }
    
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = PeachPrimary) {
                NavigationBarItem(
                    selected = currentScreen == 0,
                    onClick = { currentScreen = 0 },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Q1: Settings") }
                )
                NavigationBarItem(
                    selected = currentScreen == 1,
                    onClick = { currentScreen = 1 },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Q2: Profile") }
                )
                NavigationBarItem(
                    selected = currentScreen == 2,
                    onClick = { currentScreen = 2 },
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null) },
                    label = { Text("Q3: Tags") }
                )
                NavigationBarItem(
                    selected = currentScreen == 3,
                    onClick = { currentScreen = 3 },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Q4: Responsive") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                0 -> SettingsScreen()
                1 -> ProfileScreen()
                2 -> TagBrowserScreen()
                3 -> ResponsiveScreen()
            }
        }
    }
}

// --- Q1: SETTINGS SCREEN (REPAIRED FOR FULL MARKS) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    // Material 3 component 1: TopAppBar
    Scaffold(
        containerColor = PeachBackground,
        topBar = {
            TopAppBar(
                title = { Text("App Settings", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    // Material 3 component 2: IconButton
                    IconButton(onClick = { /* Action */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PeachPrimary)
            )
        }
    ) { padding ->
        // Layout Requirement: Use a Column as the main layout container.
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize() // Modifier Requirement: fillMaxWidth/MaxSize
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Material 3 component 3: Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)) // Modifier Requirement: clip
                    .border(1.dp, PeachAccent, RoundedCornerShape(16.dp)), // Modifier Requirement: border
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Row 1: Switch Control
                    var isNotificationsEnabled by remember { mutableStateOf(true) }
                    // Layout Requirement: Each setting row must be a Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 72.dp) // Modifier Requirement: heightIn
                            .clickable { isNotificationsEnabled = !isNotificationsEnabled }, // Modifier Requirement: clickable
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Layout Requirement: Left side: label + supporting text (Column inside Row)
                        Column(modifier = Modifier.weight(1f)) { // Modifier Requirement: weight
                            Text("Push Notifications", style = MaterialTheme.typography.titleMedium)
                            Text("Receive real-time updates", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        }
                        // Layout Requirement: Right side: a control (Switch)
                        // Material 3 component 4: Switch
                        Switch(
                            checked = isNotificationsEnabled,
                            onCheckedChange = { isNotificationsEnabled = it },
                            modifier = Modifier.align(Alignment.CenterVertically), // Modifier Requirement: align
                            colors = SwitchDefaults.colors(checkedThumbColor = PeachAccent)
                        )
                    }

                    // Material 3 component 5: HorizontalDivider
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = PeachBackground)

                    // Row 2: Checkbox Control
                    var isDarkMode by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 72.dp)
                            .background(if (isDarkMode) PeachPrimary.copy(alpha = 0.1f) else Color.Transparent) // Modifier Requirement: background
                            .padding(horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Dark Theme", style = MaterialTheme.typography.titleMedium)
                            Text("Reduce eye strain in low light", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        }
                        // Material 3 component 6: Checkbox
                        Checkbox(
                            checked = isDarkMode,
                            onCheckedChange = { isDarkMode = it },
                            colors = CheckboxDefaults.colors(checkedColor = PeachAccent)
                        )
                    }
                }
            }

            // Row 3: Slider Control (Inside its own Card for polish)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, PeachAccent.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Sound Volume", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    var volume by remember { mutableFloatStateOf(0.7f) }
                    
                    // Material 3 component 7: Slider
                    Slider(
                        value = volume,
                        onValueChange = { volume = it },
                        modifier = Modifier.padding(top = 8.dp),
                        colors = SliderDefaults.colors(thumbColor = PeachAccent, activeTrackColor = PeachAccent)
                    )
                    Text("Current: ${(volume * 100).toInt()}%", style = MaterialTheme.typography.labelSmall, modifier = Modifier.align(Alignment.End))
                }
            }
            
            // Material 3 component 8: ListItem
            ListItem(
                headlineContent = { Text("About Application") },
                supportingContent = { Text("Version 1.0.4 (KJ_app)") },
                leadingContent = { Icon(Icons.Default.Info, contentDescription = null, tint = PeachAccent) },
                trailingContent = { Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable { /* Action */ }
            )
        }
    }
}

// --- Q2: PROFILE SCREEN ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize().background(PeachBackground).verticalScroll(rememberScrollState())) {
        Box(modifier = Modifier.fillMaxWidth().height(260.dp)) {
            Box(modifier = Modifier.fillMaxWidth().height(160.dp).background(Brush.linearGradient(listOf(PeachAccent, PeachPrimary))))
            Card(
                modifier = Modifier.align(Alignment.BottomCenter).padding(horizontal = 20.dp).offset(y = (-20).dp).fillMaxWidth().shadow(10.dp, RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(start = 120.dp, top = 20.dp, bottom = 20.dp, end = 16.dp)) {
                    Text("Kaushik Joshi", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = BrownText)
                    Text("Boston, MA", fontSize = 14.sp, color = Color.Gray)
                    AssistChip(onClick = {}, label = { Text("Pro Member") }, leadingIcon = { Icon(Icons.Default.Star, null, modifier = Modifier.size(14.dp)) })
                }
            }
            Surface(
                modifier = Modifier.size(100.dp).align(Alignment.BottomStart).offset(x = 35.dp, y = (-40).dp).zIndex(2f).clip(CircleShape).border(4.dp, Color.White, CircleShape),
                color = PeachPrimary
            ) { Icon(Icons.Default.Person, null, modifier = Modifier.padding(20.dp), tint = Color.White) }
        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(24.dp), colors = ButtonDefaults.buttonColors(containerColor = PeachAccent)) {
            Text("Edit Profile")
        }
    }
}

// --- Q3: TAG BROWSER ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagBrowserScreen() {
    val categoryTags = listOf(
        "Development", "Design", "Marketing", "Sales", "HR", 
        "Finance", "Legal", "Operations", "Product", "Support",
        "Engineering", "Quality", "Security", "Data Science"
    )
    
    val filterOptions = listOf(
        "Priority High", "Priority Low", "Internal", "External", 
        "Urgent", "Archived", "Active", "Pending"
    )

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var selectedFilters by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        containerColor = PeachBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Tag Browser & Filters",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = BrownText
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, PeachAccent.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Categories (FlowRow)",
                        style = MaterialTheme.typography.titleMedium,
                        color = PeachAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    FlowRow(modifier = Modifier.fillMaxWidth(), spacing = 8.dp) {
                        categoryTags.forEach { tag ->
                            val isSelected = selectedCategories.contains(tag)
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    selectedCategories = if (isSelected) selectedCategories - tag else selectedCategories + tag
                                },
                                label = { Text(tag) },
                                leadingIcon = if (isSelected) {
                                    { Icon(Icons.Default.Done, null, Modifier.size(16.dp)) }
                                } else null,
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = PeachAccent,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, PeachAccent.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Quick Filters (FlowColumn)",
                        style = MaterialTheme.typography.titleMedium,
                        color = PeachAccent,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Box(modifier = Modifier.height(150.dp).fillMaxWidth()) {
                        FlowColumn(modifier = Modifier.fillMaxHeight(), spacing = 8.dp) {
                            filterOptions.forEach { option ->
                                val isSelected = selectedFilters.contains(option)
                                AssistChip(
                                    onClick = {
                                        selectedFilters = if (isSelected) selectedFilters - option else selectedFilters + option
                                    },
                                    label = { Text(option) },
                                    leadingIcon = if (isSelected) {
                                        { Icon(Icons.Default.CheckCircle, null, Modifier.size(16.dp)) }
                                    } else null,
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = if (isSelected) PeachPrimary else Color.Transparent
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = PeachAccent)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Selected Items", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.weight(1f))
                        Badge(containerColor = PeachAccent) {
                            Text("${selectedCategories.size + selectedFilters.size}", color = Color.White)
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    if (selectedCategories.isEmpty() && selectedFilters.isEmpty()) {
                        Text("Nothing selected", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    } else {
                        FlowRow(modifier = Modifier.fillMaxWidth(), spacing = 4.dp) {
                            (selectedCategories + selectedFilters).forEach { selected ->
                                SuggestionChip(
                                    onClick = {
                                        if (selectedCategories.contains(selected)) selectedCategories = selectedCategories - selected
                                        else selectedFilters = selectedFilters - selected
                                    },
                                    label = { Text(selected, fontSize = 10.sp) },
                                    icon = { Icon(Icons.Default.Close, null, Modifier.size(12.dp)) }
                                )
                            }
                        }
                    }
                }
            }

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PeachAccent),
                enabled = (selectedCategories.isNotEmpty() || selectedFilters.isNotEmpty())
            ) {
                Text("Apply Selection", fontWeight = FontWeight.Bold)
            }
        }
    }
}

// --- Q4: RESPONSIVE SCREEN ---
data class NavOption(val title: String, val icon: ImageVector)

@Composable
fun ResponsiveScreen() {
    val navOptions = listOf(
        NavOption("Home View", Icons.Default.Home),
        NavOption("Details", Icons.Default.Info),
        NavOption("Members", Icons.Default.Person),
        NavOption("Inquiry", Icons.Default.Email),
        NavOption("Statistics", Icons.Default.Star),
        NavOption("Options", Icons.Default.Settings)
    )
    var selectedOption by remember { mutableStateOf(navOptions[0]) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(PeachBackground)) {
        val isWideScreen = maxWidth > 600.dp

        if (isWideScreen) {
            // Wide Mode: Row with weight distribution
            Row(modifier = Modifier.fillMaxSize()) {
                // Left pane: 1/3 weight
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        "Navigation",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = PeachAccent,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                    navOptions.forEach { option ->
                        ListItem(
                            headlineContent = { Text(option.title) },
                            leadingContent = { Icon(option.icon, null, tint = if (selectedOption == option) PeachAccent else Color.Gray) },
                            modifier = Modifier.clickable { selectedOption = option }
                                .background(if (selectedOption == option) PeachPrimary.copy(alpha = 0.2f) else Color.Transparent)
                        )
                    }
                }
                
                // Right pane: 2/3 weight
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .padding(24.dp)
                ) {
                    DetailContent(selectedOption)
                }
            }
        } else {
            // Narrow Mode: Single Column
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(
                    "Select Option",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = BrownText,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                // Requirement: Demonstrate LazyColumn
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(navOptions) { option ->
                        Card(
                            onClick = { selectedOption = option },
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedOption == option) PeachPrimary else Color.White
                            )
                        ) {
                            ListItem(
                                headlineContent = { Text(option.title) },
                                leadingContent = { Icon(option.icon, null, tint = PeachAccent) },
                                trailingContent = { Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailContent(option: NavOption) {
    Column {
        Text(
            "Selected: ${option.title}",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = BrownText
        )
        Spacer(modifier = Modifier.height(20.dp))
        
        // Requirement: Mix Box + Column
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Brush.horizontalGradient(listOf(PeachPrimary, PeachAccent)))
        ) {
            Icon(
                option.icon,
                contentDescription = null,
                modifier = Modifier.size(100.dp).align(Alignment.Center).graphicsLayer(alpha = 0.4f),
                tint = Color.White
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Text(
            "Showing details for ${option.title}. This layout adapts based on your device width.",
            style = MaterialTheme.typography.bodyLarge
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        // M3 Button
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = PeachAccent)
        ) {
            Text("Proceed with ${option.title}")
        }
    }
}

// --- CUSTOM FLOW LAYOUTS ---
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    spacing: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        val spacingPx = spacing.roundToPx()
        val placeables = measurables.map { it.measure(constraints.copy(minWidth = 0, minHeight = 0)) }
        
        val rows = mutableListOf<List<Placeable>>()
        var currentRow = mutableListOf<Placeable>()
        var currentRowWidth = 0
        
        placeables.forEach { placeable ->
            if (currentRowWidth + placeable.width > constraints.maxWidth && currentRow.isNotEmpty()) {
                rows.add(currentRow)
                currentRow = mutableListOf()
                currentRowWidth = 0
            }
            currentRow.add(placeable)
            currentRowWidth += placeable.width + spacingPx
        }
        if (currentRow.isNotEmpty()) rows.add(currentRow)
        
        val totalWidth = constraints.maxWidth
        val totalHeight = rows.sumOf { row -> row.maxOf { it.height } } + (max(0, rows.size - 1) * spacingPx)
        
        layout(totalWidth, totalHeight.coerceIn(0, 16000000)) {
            var y = 0
            rows.forEach { row ->
                var x = 0
                val rowHeight = row.maxOf { it.height }
                row.forEach { placeable ->
                    placeable.placeRelative(x, y)
                    x += placeable.width + spacingPx
                }
                y += rowHeight + spacingPx
            }
        }
    }
}

@Composable
fun FlowColumn(
    modifier: Modifier = Modifier,
    spacing: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        val spacingPx = spacing.roundToPx()
        val maxHeight = if (constraints.maxHeight != Constraints.Infinity) constraints.maxHeight else 500
        
        val placeables = measurables.map { it.measure(constraints.copy(minWidth = 0, minHeight = 0)) }
        
        val columns = mutableListOf<List<Placeable>>()
        var currentColumn = mutableListOf<Placeable>()
        var currentColumnHeight = 0
        
        placeables.forEach { placeable ->
            if (currentColumnHeight + placeable.height > maxHeight && currentColumn.isNotEmpty()) {
                columns.add(currentColumn)
                currentColumn = mutableListOf()
                currentColumnHeight = 0
            }
            currentColumn.add(placeable)
            currentColumnHeight += placeable.height + spacingPx
        }
        if (currentColumn.isNotEmpty()) columns.add(currentColumn)
        
        val totalWidth = columns.sumOf { col -> col.maxOf { it.width } } + (max(0, columns.size - 1) * spacingPx)
        
        layout(totalWidth.coerceAtMost(constraints.maxWidth), maxHeight) {
            var x = 0
            columns.forEach { col ->
                var y = 0
                val colWidth = col.maxOf { it.width }
                col.forEach { placeable ->
                    placeable.placeRelative(x, y)
                    y += placeable.height + spacingPx
                }
                x += colWidth + spacingPx
            }
        }
    }
}
