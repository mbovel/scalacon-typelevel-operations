---
title: Scala type-level operations
author: "[Matt Bovel](mailto:matthieu@bovel.net) @[LAMP](https://www.epfl.ch/labs/lamp/)/[LARA](https://lara.epfl.ch/w/), [EPFL](https://www.epfl.ch/fr/)"

---

# Introduction

## Rockets explode

<div class="float">

<img src="images/Explosion_of_first_Ariane_5_flight_June_4_1996.jpg" width="600px" height="395px">

<small>[Explosion of the first Ariane 5 flight, June 4, 1996<br/>© ESA (European Space Agency)](https://www.esa.int/ESA_Multimedia/Images/2009/09/Explosion_of_first_Ariane_5_flight_June_4_1996)</small>

</div>

<div style="height: 395px; display: flex; align-items: center; justify-content: center;">

$$
\gdef\tm#1{\textcolor{953800}{\texttt{{#1}}}}
\gdef\tp#1{\textcolor{8250df}{\texttt{{#1}}}}
\tm{x}
$$

</div>

<aside class="notes">

**Title Slide**

Hello, my name is Matt Bovel and I am a PhD student at EPFL. I am working at LAMP, the lab that builds Scala. It's just next to the Scala center. And I am co-supervised by Martin Odersky that you all know.

My research interests are center around what we can qualify as precise types and so today, I will talk about precise types. More specifically, I will mainly talk about singleton types, refinement types, match types and type-level operations. I will give a pretty general presentation. It's not very much research-oriented. Instead, after a very brief introduction the presentation will be completely examples-based. I will show practical examples of what we can achieve with the type system of Scala today, and also what we might be able to achieve in the future. So even if you already know everything about these precise types, you might be interested in this later part.

**TOC**

Ok, before diving into the subject, lets me zoom out for a few slides.

**This slide**

Writing software is hard. It takes time. And even so, all programs that we use everyday are full of bugs. Software bugs cost the U.S. economy an estimated \$59.5 billion annually, or about 0.6 percent of the gross domestic product. So we lose time, money, and in extreme situation, this can lead to catastrophes like the crash of the Ariane 5 rocket—from which you have a picture here.

How can we help? And _what_ can help?

</aside>


## Types can help

<div class="float">

<img src="images/Artist_s_view_of_the_configuration_of_Ariane_6_using_four_boosters_A64_pillars.jpg" width="600px" height="395px">

<small>[Artist's view of the configuration of Ariane 6 using four boosters (A64)<br/>© ESA (European Space Agency)](https://www.esa.int/ESA_Multimedia/Images/2018/06/Artist_s_view_of_the_configuration_of_Ariane_6_using_four_boosters_A643)</small>

</div>

<div style="height: 395px; display: flex; align-items: center; justify-content: center;">

$$\tm{x}:\tp{Long}$$

</div>


<aside class="notes">

Well, one of the things that can help are _type systems_. The idea of a type system is to assign a type to each element of a program to make sure that these elements are combined appropriately. If I want to sum two elements and I write $x + y$ for example, I want to make sure that $x$ and $y$ are numbers. If these are something else, then operation written $+$ might have a complete different meaning or not make sense at all.



</aside>
