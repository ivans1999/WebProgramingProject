var quotes=[

    '“Always forgive your enemies; nothing annoys them so much.” ― Oscar Wilde',
    '“A friend is someone who knows all about you and still loves you.” ― Elbert Hubbard',
    '“To live is the rarest thing in the world. Most people exist, that is all.” ― Oscar Wilde',
    '“I am so clever that sometimes I don\'t understand a single word of what I am saying.” ― Oscar Wilde',
    '“You only live once, but if you do it right, once is enough.” ― Mae West',
    '“A room without books is like a body without a soul.” ― Marcus Tullius Cicero',
    '“So many books, so little time.” ― Frank Zappa',
    '“I have always imagined that Paradise will be a kind of library.” ― Jorge Luis Borges',
    '“If you only read the books that everyone else is reading, you can only think what everyone else is thinking.” ― Haruki Murakami',
    '“You can never get a cup of tea large enough or a book long enough to suit me.” ― C.S. Lewis',
    '“There is no friend as loyal as a book.” ― Ernest Hemingway',
    '“I find television very educating. Every time somebody turns on the set, I go into the other room and read a book.” ― Groucho Marx',
    '“Books are a uniquely portable magic.” ― Stephen King',
    '“Be careful about reading health books. Some fine day you\'ll die of a misprint.” ― Markus Herz',
    '“Think before you speak. Read before you think.” ― Fran Lebowitz',
    '“Good books don\'t give up all their secrets at once.” ― Stephen King',
    '“Books may well be the only true magic.” ― Alice Hoffman',
    '“Books are mirrors: you only see in them what you already have inside you.” ― Carlos Ruiz Zafón',
    '“There are worse crimes than burning books. One of them is not reading them.” ― Joseph Brodsky',
    '“But luxury has never appealed to me, I like simple things, books, being alone, or with somebody who understands.” ― Daphne du Maurier',
    '“Books are like mirrors: if a fool looks in, you cannot expect a genius to look out.” ― J.K. Rowling',
    '“That\'s the thing about books. They let you travel without moving your feet.” ― Jhumpa Lahiri',
    '“Books are my friends, my companions. They make me laugh and cry and find meaning in life.” ― Christopher Paolini',
    '“I read a book one day and my whole life was changed.” ― Orhan Pamuk',
    '“Books are for people who wish they were somewhere else.” ― Mark Twain',
    '“Books have a unique way of stopping time in a particular moment and saying: Let’s not forget this.” ― Dave Eggers',
    '“All the secrets of the world are contained in books. Read at your own risk.” ― Lemony Snicket',
    '“My Best Friend is a person who will give me a book I have not read.” ― Abraham Lincoln',
    '“There are books of which the backs and covers are by far the best parts.” ― Charles Dickens',
    '“Fools talk, cowards are silent, wise men listen.” ― Carlos Ruiz Zafon',
    '“A good book is an event in my life.” ― Stendhal',
    '“A half-read book is a half-finished love affair.” ― David Mitchell',
    '“The helpful thought for which you look is written somewhere in a book.” ― Edward Gorey',
    '“If you would tell me the heart of a man, tell me not what he reads, but what he rereads.” ― Francois Mauriac',
    '“A book lying idle on a shelf is wasted ammunition.” ― Henry Miller',
    '“A house without books is like a room without windows.” ― Horace Mann',
    '“I am too fond of reading books to care to write them.” ― Oscar Wilde',
    '“I feel free and strong. If I were not a reader of books I could not feel this way.” ― Walter Tevis',
    '“If I read a book and it makes my whole body so cold no fire can ever warm me, I know that is poetry.” ― Emily Dickinson',
    '“All human wisdom is contained in these two words - Wait and Hope” ― Alexandre Dumas',
    '“The worst thing about new books is that they keep us from reading the old ones.” ― Joseph Joubert',
    '“The covers of this book are too far apart.” ― Ambrose Bierce',
    'Why can\'t people just sit and read books and be nice to each other?” ― David Baldacci',
    '“Honesty is the first chapter of the book wisdom.” ― Thomas Jefferson',
    '“A book is a dream that you hold in your hands.” ― Neil Gaiman',
    '“Hungry man, reach for the book: it is a weapon.” ― Bertolt Brecht',
    '“We live and breathe words.” ― Cassandra Clare',
    '“People don\'t realize how a man\'s whole life can be changed by one book.” ― Malcolm X'
];

function newQuote(){

   

    var randomNumber = Math.floor(Math.random() * (quotes.length));
    document.getElementById('quote').innerHTML = quotes[randomNumber];

    
}