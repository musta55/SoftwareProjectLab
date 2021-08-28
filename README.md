# SoftwareProjectLab

 
Emotion detector tool can be divided into two parts:  
1.Experiment part  
2.Application part.  
 
Experiment Part 
In the experiment part, the “emotion detection from text” algorithm is implemented 
from the paper named “Emotion Detection and Analysis on Social media.” [1]   Emotion is detected in Bengali, English, and both languages together.
Before jump into the algorithm, let us discuss the dataset. 

 
This algorithm has two phases: 
Phase One: Natural Language Processing 
Natural language processing (NLP) is a branch of artificial language that helps computers understand, interpret and manipulate human language. The process is given below:

•	Tokenization: Converting the given text into tokens.
•	Stop word removal: Removing unnecessary parts of speech, punctuation.
•	Lemmatization: Converting words into base form.
  
 
Firstly, texts are formed into tokens using the tokenization method. Then punctuation, preposition, articles, etc. are removed using the stop word removal process. Then, those remaining tokens are ready for lemmatizing and are converted to base words. 
In the Bengali language, firstly words are tokenized and lemmatized using a Bengali lemmatizer (a database). Then tokens are translated to Bengali and are ready for further processing with English tokens. 

Phase Two: Lexical Affinity Method 
•	Emotion Hits: Matching emotion from the emotion-word database and counting frequency for scoring. 
The final emotional score (Score) is an eight-tuple, which is calculated by summing over all the hits (of a particular Emotion-Category) in all the sentences in the text for each of the eight Emotion-Categories (ECj, j=1,2,...8).
The equation is given below: [1]  
Variables:
emotScore= emotion score,
perScore= Base score for any hits.
relScore= Relative score.
 
	Score[ECj ] =   ∑All 	 Hits of EC X j ∗ (emotScore ∗ perScore)      (1)  
	RelScore[ECj ] =  	 Score[ECj ] 	 	 (2) 
 
•	Intensity check: Checking the intensity of hits emotions.
•	Negation check: Checking the negation of hits emotions, will make an impact on the calculation. 
 
 
On the application side, a user has to log in or sign up for this application. there are three types of inputs: 
 
•	Website links 
•	Facebook 
•	 Article 
Website links are used for fetching data from the website. Jsoup library is used for this. After receiving data, headers are removed manually, then the rest of the data are ready to operate.

Using Facebook, this tool takes access tokens from a user and fetches all public posts, reactions, etc. Restfb API is used during this process. This API calls developers.facbook.com and using graph API explorer, takes the access token of a certain user. With this access token, it can fetch all posts.

Article section is used for users to post their thoughts and texts emotions are stored according to the user profile.

 Analysis Part 
•	Accuracy check: To justify the correctness of emotion detection with peoples’ reactions to a certain post by finding the relationship between emotion and reaction.
•	Reaction Prediction: To predict emotion according to the reacts of Facebook using the linear regression model. 
•	Emotional wheel: To detect consistency, mental stability, mean emotions, recent emotion, and intensity. 
o	Consistency: To identify how consistent his/her posts are. If a users’ sentiment is low or high for 3 consecutive posts, then his/her emotional consistency is significant. 
o	Stability: To detect potential mood swing conditions by these inputs. 
o	Intensity: To figure out how intense a users’ emotions were. If his/her posts’ score is more than the normal score, then his/her emotional intensity is high.
•	Sentiment analysis: To find emotional changes according to timelines of Facebook posts. 
•	Emotional Dashboard: Where all emotions of Facebook, weblink, and article posts are shown on a single page. 
•	Report Generate: To get feedback on his/her mental condition based on the emotional wheel. 
•	Fuzzification: To find out the personality of individuals. 
 
o	Identify fuzzy sets: Data from emotional wheels (Emotional consistency, stability, combined emotion, and intensity) are used for fuzzy sets. They each have three values; low, medium, and high. 
 
o	And Operation Rule (If-Then Logic): Using the if-then inference rule, all fuzzy sets are ready for the centroid method for defuzzification [11]

   
If-then inference logic
o	Centroid Defuzzification method: Then these fuzzy values are converted to crisp sets using the center of sum method. Max value of crisp value is considered to be the result of personality [10] 
 
Formula: 
Z* = ∑   𝐴𝑥 
∑𝑨
 
Where ∑A is a membership function and x is a mean value. 


    Defuzzification technique
That’s how the personality of a user is determined based on fuzzy logic. 
