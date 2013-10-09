Ultimate_Theme_UI_Template
==========================

The ultimate open source Android theme and icon pack template

This is utilizing tabs and a card listview to provide 3 (or more) swipable tabs that each have their own cardui listview for different actions.

The tabs are utilizing https://github.com/astuetz/PagerSlidingTabStrip

The cards are utilizing https://github.com/afollestad/Cards-UI

At the current moment, I am not including the libraries in the code so you will need to import them in order for this to work. I will include the libraries in the future.

A brief explanation of the functionality:

The ThemeActivity contains the main functionality and calls to the tabs:
The tabs are Theme, Extras, Contact and About. 
Theme includes cards to apply in multiple launchers (Action Launcher, ADWEX Launcher, Apex Launcher, Nova Launcher, Smart Launcher and Go Launcher EX).
Extras includes cards for your Play Store link, Wallpapers, Icons (this displays icons included using string-arrays in the icon_pack), an activity that will scan the device it is installed on, capture the activity names and their icons and zip it to send).
It also includes Cards to do with as your choice, I have added UCCW and Zooper in their as place holders as well as some fun.
The Contact included cards for social media, email and web.

The IconActivity calls to the different icon category tabs.

The icon section will be modified in the future. I will be implementing new code that will auto display based on file name (i.e. apps_, system_,misc_) which will get rid of the strin-arrays.

Feel free to contact me for questions via the Gplus or FB links. Do not ask me how to make a theme, I will not help you. I will however provide direction to using the template.

By agreeing to use this source, you are agreeing you have a sense of humour. 

I have moved most items to be strings and will expand more as well as expand supported launchers and their additional features (Go features, Smart features etc).




