# PunkApiSample
Public   API</br>
The   beer   list   can   be   accessed   through   the   PUNK   API:    https://punkapi.com/documentation/v2
</br></br>
Beer   Details   Screen</br>
Fetch beers and   display for   each   beer   on   one   screen:</br> 
● Image</br>
● Name</br>
● ABV</br>
● Description</br>
● Display all Hops in a list</br>
● Display all Malts in a list</br>
● Display all Methods in a list</br>
</br></br>
Business   Requirements</br>
1. For   each   element   of   each   list   (i.e.   Hops,   Malts   &   Methods), show  a   button   with   two   possible states  -   IDLE   and   DONE;
2. The   buttons   should   start   in   the    IDLE    state.
3. When   the   button   is   clicked   there   is   a   transition   to   the    DONE    status,   indicating   that   the
ingredient   has   been   used   or   the   method   has   been   applied;
4. In   the   case   where   a   method   has   a   duration,   when   the   button   is   tapped   a   countdown
timer   will   start   for   the   duration.   While   the   countdown   is   running   if   the   user   taps   on   the button   again   the   timer   will   be    paused .   If   the   user   taps   on   the   button   while   it   is   paused,
the  timer  will   resume .  When  the  countdown  finishes,  the  button  will  transition  to  the DONE
state.  The  button  in  this  case  needs  two  extra  states, RUNNING  &   PAUSED .
5. A  hop  element  with  the  attribute   add=middle   can’t  be    until  all  the  hops  with
add=start    are   done;
6. A  hop  element  with  the  attribute   add=end   can’t  be   DONE   until  all  the  hops  with
add=middle     are   done;
7. When   ABV   is   more   than   10   add   a   white   flag   “STRONG”   to   the   image
8. When   IBU   is   more   than   90   add   a   white   flag   “BITTER”   to   the   image
9. When   ABV   is   more   than   10   and   IBU   is   more   than   90   add   a   white   flag   “STRONG & BITTER”
