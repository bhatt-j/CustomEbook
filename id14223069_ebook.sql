-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 02, 2020 at 04:35 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id19310011_ebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `Author_master`
--

CREATE TABLE `Author_master` (
  `id` int(10) NOT NULL,
  `name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Author_master`
--

INSERT INTO `Author_master` (`id`, `name`, `img`) VALUES
(1, 'jay vasavada', 'https://ebook1sql.000webhostapp.com/authorimg/jaybhai.jpeg'),
(2, 'Chetan Bhagat', 'https://ebook1sql.000webhostapp.com/authorimg/Chetan_Bhagat.jpg'),
(3, 'Kaajal Oza', 'https://ebook1sql.000webhostapp.com/authorimg/kajal%20oza.jpg'),
(4, 'Munshi Premchand', 'https://ebook1sql.000webhostapp.com/authorimg/Munshi%20premchand.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `bookmark_master`
--

CREATE TABLE `bookmark_master` (
  `id` int(10) NOT NULL,
  `book_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_img` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_rate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `book_category` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `book_category2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `by_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `book_desc` varchar(600) COLLATE utf8_unicode_ci NOT NULL,
  `flag` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Done',
  `book_url` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `u_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category_master`
--

CREATE TABLE `category_master` (
  `id` int(10) NOT NULL,
  `cat_name` varchar(55) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category_master`
--

INSERT INTO `category_master` (`id`, `cat_name`) VALUES
(1, 'Comic'),
(2, 'Business'),
(3, 'Entertainment'),
(4, 'God'),
(5, 'Sports'),
(6, 'Novel');

-- --------------------------------------------------------

--
-- Table structure for table `order_master`
--

CREATE TABLE `order_master` (
  `id` int(10) NOT NULL,
  `book_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_url` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `book_img` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_desc` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `book_category` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `by_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_email` varchar(210) COLLATE utf8_unicode_ci NOT NULL,
  `total_pay` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `readingbook_master`
--

CREATE TABLE `readingbook_master` (
  `id` int(10) NOT NULL,
  `book_name` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `book_img` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_rate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `book_category` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `book_category2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `by_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `book_desc` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `flag` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'new',
  `book_url` varchar(300) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `readingbook_master`
--

INSERT INTO `readingbook_master` (`id`, `book_name`, `book_img`, `book_rate`, `book_category`, `book_category2`, `by_name`, `book_desc`, `flag`, `book_url`) VALUES
(2, 'Never Split the Difference: Negotiating As If Your Life Depended On It', 'https://ebook1sql.000webhostapp.com/bookcover/NeverSplit.jpg', '100', 'Business', 'Business and Economica', 'Chris Voss', 'After a stint policing the rough streets of Kansas City, Missouri, Chris Voss joined the FBI, where his career as a kidnapping negotiator brought him face-to-face with bank robbers, gang leaders and terrorists. Never Split the Difference takes you inside his world of high-stakes negotiations, revealing the nine key principles that helped Voss and his colleagues succeed when it mattered the most – when people’s lives were at stake.  Rooted in the real-life experiences of an intelligence professional at the top of his game, Never Split the Difference will give you the competitive edge in any discussion.', '0', 'https://ebook1sql.000webhostapp.com/Books/Business/Never%20Split%20the%20Difference_%20Negotiating%20As%20If%20Your%20Life%20Depended%20On%20It%20(%20PDFDrive.com%20).pdf'),
(3, 'Your Money or Your Life!', 'https://ebook1sql.000webhostapp.com/bookcover/your%20money.jpg', '200', 'Business', 'Business and Finance', 'George Bernard Shaw', 'Your Money Or Your Life is different than other personal finance books because it emphasizes choice, freedom and fulfillment rather than deprivation and discipline. You\'ll learn that money is life energy and how to become conscious of the movement of your money so it provides you maximum fulfillment.', 'new', 'https://ebook1sql.000webhostapp.com/Books/Business/your-money-or-your-life.pdf'),
(4, 'Strategic Leadership', 'https://ebook1sql.000webhostapp.com/bookcover/Strategic%20Leadership.jpg', '0', 'Business', ' Business & Career ', 'Morrill, Richard L.', 'Strategic leadership is what modern leadership is all about. Organizations expect leaders to anticipate and be proactive more than ever before. In this book, the authors draw on their vast experience working direct...', 'new', 'https://ebook1sql.000webhostapp.com/Books/munshi%20premchand/Godaan%20The%20Gift%20of%20Cow.pdf'),
(5, 'The Warren Buffett Way: The World’s Greatest Investor', 'https://ebook1sql.000webhostapp.com/bookcover/The%20Warren%20Buffett%20Way%20The%20World%E2%80%99s%20Greatest%20Investor.jpg', '30', 'Business  ', 'Business & Career  ', 'Bill Miller', 'The Warren Buffett Way: Investment Strategies of The World\'s Greatest Investor is a biography of the infamous corporate tycoon, Warren Buffet. The book includes the various personal trials and tribulations of Buffet, as well as his business acumen.', '0', 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf'),
(6, 'Rajkosh ke lutere', 'https://ebook1sql.000webhostapp.com/bookcover/rajkosh%20ke%20lutere.jpg', '100', 'Comic', 'Comics ', ' Sanjay Gupta ', 'राजकोष का लुटेरा डाकू सुल्तान सिंह कई राज्यों का राजकोष लूटता हुआ विशालगढ़ में जा पहुंचा। डाकू के भय से कई दूसरे राज्यों ने अपना खजाना राजा विक्रम सिंह के अति सुरक्षित राजकोष में रखवा दिया था। डाकू सुल्तान सिंह बांकेलाल की मदद से पूरा राजकोष ही लूट कर ले गया। राजा विक्रम सिंह ने डाकूओं के मददगार को फांसी देने का ऐलान किया तो उड़ गए बांकेलाल के होश। अब बांकेलाल कैसे बचेगा फांसी के फंदे से?', 'new', ''),
(7, 'Captain Cool: M S Dhoni', 'https://ebook1sql.000webhostapp.com/bookcover/Caption%20cool.jpg', '353', 'Sports', 'Sports', 'Gulu Ezekiel', 'Mahendra Singh Dhoni is as calm and unruffled a sportsman on the field as he is self-effacing off it. But \'brute strength\', \'murderous form\' and \'a man possessed\' were some of the phrases that came to mind when, on 5 April 2005 in Visakhapatnam, he exploded onto international consciousness by becoming the first regular Indian keeper to score a one-day century. With his striking form on the day, his long locks visible beneath his helmet, red tints glinting in the sunlight, \'Mahi\' Dhoni had transformed from a boy Hailing from an obscure small town to a sports legend with the aura of a rock star. And yet, Dhoni was no child prodigy, no overnight success. When he made his international debut at 23, he was already mature by Indian cricket standards - with five grinding years of domestic cricket behind him. How that legend came to be and grew from game to game, is told here by noted sportswriter Gulu Ezekiel in his crackling but measured prose.', '0', 'https://ebook1sql.000webhostapp.com/Books/Sports/Captain%20Cool_%20M%20S%20Dhoni%20(%20PDFDrive.com%20).pdf'),
(8, 'Playing It My Way', 'https://ebook1sql.000webhostapp.com/bookcover/Playingitmywaybookcover.jpeg', '0', 'Sports', 'Sports', 'Sachin Tendulkar', '', 'new', 'https://ebook1sql.000webhostapp.com/Books/Sports/Playing%20It%20My%20Way_Sachin%20Tendulkar.pdf'),
(9, 'Godaan The Gift of Cow', 'https://ebook1sql.000webhostapp.com/bookcover/Godaan%20The%20Gift%20of%20Cow.jpg', '0', 'Novel', 'Novel', 'Munshi Premchand', 'Godaan (The Gift of a Cow), a classic novel by the Indian author Premchand, sheds some light on these questions. The story chronicles the failed lifelong quest of a poor peasant to scrape together enough money to buy a cow for his godaan-the traditional gift presented to a Brahmin at the time of someone\'s death', 'new', 'https://ebook1sql.000webhostapp.com/Books/munshi%20premchand/Godaan%20The%20Gift%20of%20Cow.pdf'),
(10, 'Half Girlfriend ', 'https://ebook1sql.000webhostapp.com/bookcover/half%20girlfriend.jpg', '90', 'Novel', 'Novel', 'Chetan Bhagat', 'Half Girlfriend is an Indian English coming of age, young adult romance novel by Indian author Chetan Bhagat. The novel, set in rural Bihar, New Delhi, Patna, and New York, is the story of a Bihari boy in quest of winning over the girl he loves.', '0', 'https://ebook1sql.000webhostapp.com/Books/Chetan%20bhagat/Half%20Girlfriend%20by%20Chetan%20Bhagat.pdf'),
(11, '2 States: The Story of My Marriage', 'https://ebook1sql.000webhostapp.com/bookcover/Chetan%20Bhagats%202%20States.jpg', '177', 'Novel', 'Novel', 'Chetan Bhagat', '2 States: The Story of My Marriage commonly known as 2 States is a 2009 novel written by Chetan Bhagat. It is the story about a couple coming from two different states in India, who face hardships in convincing their parents to approve of their marriage.', 'new', 'https://ebook1sql.000webhostapp.com/Books/Chetan%20bhagat/2-states-the-story-of-my-marriage-by-chetan-bhagat.pdf'),
(12, 'One indian girl', 'https://ebook1sql.000webhostapp.com/bookcover/One%20indian%20girl.jpg', '95', 'Novel', 'Novel', 'Chetan Bhagat', 'One Indian Girl is the story of Radhika Mehta, a worker at the Distressed Debt group of Goldman Sachs, the investment bank. The book begins with Radhika making arrangements regarding her marriage with Brijesh Gulati who works in the Facebook company in San Francisc', 'new', 'https://ebook1sql.000webhostapp.com/Books/Chetan%20bhagat/One%20Indian%20Girl.pdf'),
(13, 'The 3 Mistakes of My Life', 'https://ebook1sql.000webhostapp.com/bookcover/Three%20mistakes%20%20of%20my%20life.jpg', '50', 'Novel', 'Novel', 'Chetan Bhagat', 'The 3 Mistakes of My Life is a new book from Chetan Bhagat, a well-known author, and writer. The story lines based on love, historical disasters, religion, friendship, cricket, excitement, business, and humor.', '0', 'https://ebook1sql.000webhostapp.com/Books/Chetan%20bhagat/three%20mistakes%20of%20my%20life.pdf'),
(14, 'Ek Sanjhne Sarname', 'https://ebook1sql.000webhostapp.com/bookcover/Ek%20sanj%20na%20sarname.jpg', '150', 'Novel', 'Novel', 'Kaajal Oza', 'A story of love with the setting sun and effort to write a definition of love in a new perspective. The emotions of women from different places and their sentiments with these sentiments a new eras story is woven in vivid shades.', 'new', 'https://ebook1sql.000webhostapp.com/Books/kajal%20oza/%E0%AA%8F%E0%AA%95%20%E0%AA%B8%E0%AA%BE%E0%AA%82%E0%AA%9C%20%E0%AA%A8%E0%AA%BE%20%E0%AA%B8%E0%AA%B0%E0%AA%A8%E0%AA%BE%E0%AA%AE%E0%AB%87%20%E0%AA%95%E0%AA%BE%E0%AA%9C%E0%AA%B2%20%E0%AA%93%E0%AA%9D%E0%AA%BE.pdf'),
(15, 'Blue Book', 'https://ebook1sql.000webhostapp.com/bookcover/Blue%20book.jpg', '120', 'Novel', 'Novel', 'Kaajal Oza', 'કોઈ પણ ઉમરનો પુરુષ હોય, ૩..૧૩..૩૩..૪૩..કે ૭૩, જયારે પુરુષ ની વાત થતી હોય ત્યારે મોટા ભાગની સ્ત્રી ઓનો અભિપ્રાય એકસરખો હોય છે? પુરુષ નો સ્વભાવ,પ્રકૃતિ અને પ્યાસ કોઈ પણ ઉમરે એકસરખી રહે છે? પુરુષની \" દ્રષ્ટિ \" અને \"નજર\" અલગ અલગ હોય છે? એને શર્રીરથી આગળ કઈ સમજાતું કે દેખાતું નથી? માન્યતાઓ ઘણી છે, અભિપ્રાયો પણ ઘણા છે.... પરંતુ \'પુરુષ\' ના મનને સમજવાની કોઈ માસ્ટર કી હજી સુધી મળી નથી ! આમ તો કેહવાય છે કે સ્ત્રીનું મન અકળ છે,સ્ત્રીને સમજાવી અઘરી છે, પણ મજાની વાત એ છે કે પુરુષ પણ એટલો જ અકળ અને ન સમજી શકાય એવો હોય છે,ક્યારેક. પુરુષને જોવાની સ્ત્રીની દ્રષ્ટી એના રોલ સાથે બદલાય છે.', 'new', 'https://ebook1sql.000webhostapp.com/Books/kajal%20oza/Blue%20Book.pdf'),
(16, 'The Everything Kids Learning Activities Book: 145 Entertaining Activities and Learning Games for Kids', 'https://ebook1sql.000webhostapp.com/bookcover/The%20Everything%20Kids\'%20Learning%20Activities%20Book145%20Entertaining%20Activities%20and%20Learning%20Games%20for%20Kids.jpg', '100', 'Entertainment', 'Entertainment', 'Amanda Morin', 'These activities are geared for kids aged 5–12, making this a go-to resource for years to come. And most activities use materials that are in your house! This easy-to-use guide is full of creative ideas and expert advice to help you be your kids\' best learning partner.', 'new', 'https://ebook1sql.000webhostapp.com/Books/Entertainment/The%20everything%20kids\'%20learning%20activities%20book%20_%20145%20entertaining%20activities%20and%20learning%20games%20for%20kids%20(%20PDFDrive.com%20).pdf'),
(17, 'Cristiano and Leo_ The Race to Become the Greatest Football Player of All Time ', 'https://ebook1sql.000webhostapp.com/bookcover/Cristiano%20and%20Leo%20The%20Race%20to%20Become%20the%20Greatest%20Football%20Player%20of%20All%20Time.jpg', '330', 'Sports', 'Sports', 'Jimmy Burns', 'Cristiano and Leo. This is their definitive story, from children kicking a ball halfway around the world from each other to their era-defining battle to be number one. One the preening Adonis, a precision physical machine who blows teams away with his pace and power.', 'new', 'https://ebook1sql.000webhostapp.com/Books/Sports/Cristiano%20and%20Leo_%20The%20Race%20to%20Become%20the%20Greatest%20Football%20Player%20of%20All%20Time%20(%20PDFDrive.com%20).pdf'),
(18, 'Rich God Poor God', 'https://ebook1sql.000webhostapp.com/bookcover/Rich%20poor%20god.jpg', '120', 'God', 'God', 'Dr john avanzini', 'Perception is not necessarily reality. Just because you perceive something a certain way doesn\'t mean it\'s true. in RICH GOD POOR GOD, Dr. John Avanzini shows you how certain misperceptions of God can put a stranglehold on your financial future. Too often, people are deceived into thinking that what that they perceive is the basis for truth. However, consider this example of how misleading your perception can be. If five blindfolded men tried to describe an elephant, they could not do an adequate job, even if each one carefuly described the part he was touching.', 'new', 'https://ebook1sql.000webhostapp.com/Books/God/Rich%20God%20Poor%20God.pdf'),
(19, 'Shreemad Bhagvad geeta', 'https://ebook1sql.000webhostapp.com/bookcover/Shreemat%20bhagwat%20book.jpeg', '0', 'God', 'God', 'Vyasa', 'Bhagavad Gita is knowledge of five basic truths and the relationship of each truth to the other: These five truths are Krishna, or God, the individual soul, the material world, action in this world, and time. The Gita lucidly explains the nature of consciousness, the self, and the universe. It is the essence of India\'s spiritual wisdom.Bhagavad Gita, is a part of the 5th Veda (written by Vedavyasa - ancient Indian saint) and Indian Epic - Mahabharata. It was narrated for the first time in the battle of Kurukshetra, by Lord Krishna to Arjun.', 'new', 'https://ebook1sql.000webhostapp.com/Books/God/Shreemat%20bhagwat%20book.pdf'),
(20, 'Godaan upanyas', 'https://ebook1sql.000webhostapp.com/bookcover/Upnays.jpg', '100', 'Novel', 'Novel', 'Munshi premchand', 'मुंशी प्रेमचंद ने जो कुछ भी लिखा है, वह आम आदमी की व्यथा कथा है, चाहे वह ग्रामीण हो या शहरी। गांवों की अव्यवस्था, किसान की तड़प, ग्रामीण समाज की विसंगतियां, अंधविश्वास, उत्पीड़न और पीड़ा की सच्ची तस्वीर प्रस्तुत करता है - गोदान। मुंशी प्रेमचंद की चिर-परिचित शैली का जीता-जागता उदाहरण है गोदान, जो जमीन से जुड़ी हकीकतों को बेनकाब करता है। विश्व की सर्वाधिक भाषाओं में अनुवाद होकर बिकने का गौरव केवल गोदान को ही प्राप्त है। ‘गोदान’ का सर्वाधिक प्रमाणिक संस्करण एक संपूर्ण उपन्यास।', '0', 'https://ebook1sql.000webhostapp.com/Books/munshi%20premchand/Godaan%20upanyas.pdf'),
(21, 'Time Management (The Brian Tracy Success Library)', 'https://ebook1sql.000webhostapp.com/bookcover/Time%20Management%20(The%20Brian%20Tracy%20Success%20Library).jpg', '250', 'Business', 'Business & managment', 'Brian Tracy', 'It’s a simple equation--the better you use your time, the more you will accomplish, and the greater you will succeed. But the rollout of this basic theory isn’t so simple, is it? In Time Management, business author and success expert Brian Tracy says it is!In this indispensable, pocket-sized guide, Tracy reveals 21 proven time management techniques you can use immediately to gain two or more productive hours every day. Two or more! Every day!!By learning the strategies that Tracy himself has identified as the most effective and employed personally, readers having trouble fitting everything the day brings them inside a 24-hour window will learn how to:• Handle endless interruptions, meetings, emails, and phone calls • Identify your key result areas • Allocate enough time for top priority responsibilities • Batch similar tasks to preserve focus and make the most of each minute.\r\n', 'new', 'https://ebook1sql.000webhostapp.com/Books/Business/time-management-mini%20(1).pdf'),
(22, 'How to Sell Yourself ', 'https://ebook1sql.000webhostapp.com/bookcover/How%20to%20Sell%20Yourself.jpg', '0', 'Business', 'Business', 'Arch Lustberg', 'COMMUNICATION IS THE transfer of information from one mind to\r\nanother mind, or to a group of other minds. It can be in the form\r\nof an idea, a fact, an image, an emotion, or a story. It can be\r\nwritten, spoken, drawn, danced, sung, or mimed.\r\nWhatever the medium, if the message doesn’t reach the other\r\nperson, there’s no communication, or there’s miscommunication.\r\nThe simple premise of this book is that every time you open\r\nyour mouth, in order for communication to happen, you have to\r\nsell yourself. If you don’t sell yourself, communication is nearly\r\nimpossible. If you do, your message will get across.\r\nWe think of selling as being product-oriented. But that’s only\r\none aspect of selling. In the case of product sales, the governing\r\nfactors are usually the salesperson and the price. Even when there’s\r\na slight price difference, we rarely buy any big-ticket item from\r\nsomeone we really dislike.', 'new', 'https://ebook1sql.000webhostapp.com/Books/Business/Confidence%20-%20How%20To%20Sell%20Yourself%20-%20Winning%20Techniques%20for%20Selling%20Yourself..Your%20Ideas...Your%20Message.pdf'),
(23, 'Island Nights Entertainments', 'https://ebook1sql.000webhostapp.com/bookcover/Island%20Nights\'%20Entertainments.jpg', '200', 'Entertainment', 'Entertainment', 'Robert Louis Stevenson', 'Island Nights\' Entertainments is an adventure collection that includes the following titles: The Beach of Falesa -- A south sea bridal -- The Ban -- The Missionary -- Devil-work -- Night in the bush -- The Bottle Imp -- The Isle of voices.', 'new', 'https://ebook1sql.000webhostapp.com/Books/Entertainment/island_nights\'_entertainments.pdf'),
(24, 'New Arabian Nights', 'https://ebook1sql.000webhostapp.com/bookcover/New%20Arabian%20Nights.jpg', '100', 'Entertainment', 'Entertainment', ' ROBERT LOUIS STEVENSON ', 'Notion Press proudly brings to you timeless classics from ancient texts to popular modern classics. This carefully chosen collection of books is a celebration of literature, our tribute to the pioneers, the legends and the giants of the literary world. Apart from being the voice of indie writers, we also want to introduce every reader to read all kinds of literature. In this series, you will find a wide range of books—from popular classics like the works of Shakespeare and Charlotte Brontë to rare gems by the likes of Edith Wharton and James Fenimore Cooper.', 'new', 'https://ebook1sql.000webhostapp.com/Books/Entertainment/new_arabian_nights.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `register_master`
--

CREATE TABLE `register_master` (
  `id` int(10) NOT NULL,
  `name` varchar(55) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(55) COLLATE utf8_unicode_ci NOT NULL,
  `contact` varchar(12) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `register_master`
--

INSERT INTO `register_master` (`id`, `name`, `email`, `password`, `contact`) VALUES
(6, 'Bhautik', '2000bhautikpatel@gmail.com', '123', '1234567890'),
(7, 'Darshak', 'pateldarshak067@gmail.com', '123', '9712834076'),
(8, 'Trivedi Kartik', 'trivedikartik769@gmail.com', 'kartik123', '6355391101');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Author_master`
--
ALTER TABLE `Author_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bookmark_master`
--
ALTER TABLE `bookmark_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_master`
--
ALTER TABLE `category_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_master`
--
ALTER TABLE `order_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `readingbook_master`
--
ALTER TABLE `readingbook_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register_master`
--
ALTER TABLE `register_master`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Author_master`
--
ALTER TABLE `Author_master`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bookmark_master`
--
ALTER TABLE `bookmark_master`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category_master`
--
ALTER TABLE `category_master`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `order_master`
--
ALTER TABLE `order_master`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `readingbook_master`
--
ALTER TABLE `readingbook_master`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `register_master`
--
ALTER TABLE `register_master`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
