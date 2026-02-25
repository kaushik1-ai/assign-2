# Assignment 3 — Jetpack Compose Layout Systems

**Student:** Kaushik Joshi
**Course:** Mobile Application Development
**Repository:** https://github.com/kaushik1-ai/assign-2.git

---

## Project Overview

This project demonstrates practical mastery of **Jetpack Compose layouts**, **Material 3 components**, and **responsive UI design** through four independent screens.

Each screen focuses on a different Compose layout concept required in the assignment rubric:

| Question | Topic             | Layout Focus         |
| -------- | ----------------- | -------------------- |
| Q1       | Settings Screen   | Row & Column mastery |
| Q2       | Profile Header    | Box layering         |
| Q3       | Tag Browser       | FlowRow & FlowColumn |
| Q4       | Responsive Layout | Phone vs Tablet UI   |

Navigation between screens is implemented using a **Material 3 Bottom Navigation Bar**.

---

# Q1 — Row / Column Mastery: Settings Screen

## Layout Design

* Main container implemented using **Column**
* Each setting item built using **Row**
* Left side contains:

  * Setting title
  * Supporting description
* Right side contains interactive controls
* `Modifier.weight()` ensures proper alignment and prevents text truncation.

## Material 3 Components Used

* TopAppBar
* Card
* Switch
* Checkbox
* Slider
* HorizontalDivider
* ListItem
* IconButton
* Button

## Modifier Usage

* padding
* fillMaxWidth
* weight
* heightIn
* align
* border
* clip
* background
* clickable

## Result

A polished and consistent settings interface following Material Design guidelines.

### Screenshot

![Q1 Settings](screenshots/q1_settings.png)

---

# Q2 — Box Layout: Profile Header + Overlay Card

## Layout Design

* **Box** used for layered composition.
* Gradient header background created using `background`.
* Circular avatar positioned above content.
* Profile card overlaps header using `offset()`.

## Key Layout Techniques

* contentAlignment
* align()
* offset()
* zIndex()
* elevation via Card shadow

## Material 3 Components

* Card
* Surface
* AssistChip
* Button
* Icon

## Modifier Requirements Demonstrated

* clip(CircleShape)
* offset positioning
* zIndex layering
* elevation/shadow
* fixed avatar size

## Result

Modern profile layout similar to real social applications.

### Screenshot

![Q2 Profile](screenshots/q2_profile.png)

---

# Q3 — FlowRow / FlowColumn: Tag Browser + Filters

## Layout Design

* **FlowRow**

  * Displays selectable category chips
  * Automatically wraps across screen width
* **FlowColumn**

  * Displays vertically wrapped filter controls
* Selected tags section updates dynamically based on user interaction.

## Interaction Features

* Tap chips to select/deselect
* Selected state changes color and icon
* Badge displays total selections
* Selected items appear in summary area

## Material 3 Components Used

* FilterChip
* AssistChip
* SuggestionChip
* Card
* Badge
* Button
* Icon
* Divider

## Modifier Requirements

* Arrangement.spacedBy for consistent spacing
* fillMaxWidth for responsiveness
* padding usage throughout
* visual selected-state styling

## Result

Responsive filtering UI inspired by modern productivity apps.

### Screenshot

![Q3 Tags](screenshots/q3_tags.png)

---

# Q4 — Responsive Layout Challenge (Phone vs Tablet)

## Responsive Strategy

Implemented using **BoxWithConstraints** to detect screen width.

### Narrow Screen (Phone)

* Single Column layout
* Navigation displayed using LazyColumn list

### Wide Screen (Tablet / Landscape)

* Layout switches to Row
* Two-pane interface:

  * Left pane → Navigation options
  * Right pane → Detail content

## Layout Features

* weight() distributes space between panes
* fillMaxHeight maintains structure
* Combination of Box + Column composition

## Material 3 Components

* Card
* ListItem
* Button
* Icon
* LazyColumn
* Navigation-style pane

## Result

Adaptive layout similar to professional tablet applications.

### Screenshot

![Q4 Responsive](screenshots/q4_responsive.png)


---

# How to Run

Clone repository:

```bash
git clone https://github.com/kaushik1-ai/assign-2.git
```

Open in **Android Studio**

Sync Gradle → Run on Emulator or Physical Device.

---

# AI Usage Disclosure

AI tools (ChatGPT) were used strictly as a learning assistant for:

* Understanding Jetpack Compose layout patterns
* Debugging layout alignment issues
* Improving modifier usage
* Structuring documentation

All implementation, testing, and integration were completed and verified independently by me.

---

