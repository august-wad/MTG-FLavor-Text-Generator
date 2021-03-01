# MTG FLavor Text Generator
# August Wadlington
 
# Setup: Ensure that assets folder is in src, that there are text files named card___.txt ranging from 1 to 250. Finally, ensure that there is a jpg file named "final product.jpg" in src folder.

# Execution: run CardMaker.main() with no arguments in the parameters.

# Description: This is a program designed to read in a large amount of Magic the Gathering flavor text, and then using Markovian algorithms, generate new flavor text based on word/phrase assosiaction and output it
# into a new card. This program works by reading text files in, tokenizing and cleaning input, and then generating phrases of a given length with a follow word, and storing these values in LinkedHashMaps. Once these
# files have been read in, the program will choose a start phrase from a list of starting phrases created during the read in process, and then use the generated maps to continually retrieve a next word until either
# the maps are unable to generate more words, or an arbitrary length is reached (done in order to ensure there was no overflow of text length).

# This system was interesting to me, as I loved playing and collecting MTG cards in middle and high school, and while I do not play in college, I still enjoy looking through new card sets and reading the flavor
# text on them. I enjoy the little blurbs and world building info shared through them, and with this project wanted to see what fun quips I could generate using a markov chain algorithm. With this output, although
# I have some issues (which I will discuss later) it has been entertaining to see what some of the resulting text has been and I am glad I attempted this. Along with the personal meaning, as a computer scientist,
# I appreciate the chance to word on a world level, as opposed to the data structures project I completed where I worked on an inidividual letter level. Along with this, I finally got to work with images, and really
# pick a program purpose that was interesting to me, which is something I am not used too in an academic setting.

# This program challenged me in working with input collection and planning (see the assets folder), as well as image generation. It is very rudimentary, but most of my work has related to text output in a terminal,
# so instead opening and working with image input, even on a rudimentary level required me to really look through and plot out how I was going to proceed. This challenge also required to backtrack and plan my code,
# which in all honesty is not something I have frequently done. Initially I tried to do my phrase storage all in one map using relevant object fields otherwise, but my class logic got too confusing, so I redid my
# storage mechanisms after planning out a more efficient way to access them via getter methods. I honestly think my next steps would be finding a better input source, as flavor text is generally too short and unique
# to work well with this kind of algorithm, but once I've done that I would like to attempt a more intense version of text generation, potentially using Google Colabs and greater analysis/generation than just a single
# probability table.

# I'm not sure I can make a determination if this system is creative or not. I think if I had more input variety and could generate at higher phrase lengths the system would appear more creative. However, I know that it still performs the same functionality
# at lower levels, it just has less data length, meaning sentences appear more choppy. I think ultimately I would say it is creative, but it feels like a very rudimentary form of creativity.

# Sources: 
#   dailymtgflavortext.tumblr.com: I used this website to collect my input data from
#   Tutorialspoint: I used this website to get some basic information on working with the BufferedImage class
