<h1>Tutorial 1</h1>

<h3>Reflection 1</h3>
A few principles of clean code that I've learned in this module are:
<ul>
<li> Meaningful names, or making sure that our variables and functions are easy to understand</li>
<li> Functions, or knowing how to write good functions</li>
<li> Good comments, or knowing when to add comments and when to make better code such that extensive comments are unnecessary.</li>
<li> Objects and data structures</li>
<li> Error handling</li>
</ul>
Through reflecting on my code, I feel confident that I've applied meaningful names and clear code such that comments are not very necessary. My objects and data structures are also well implemented.
My functions could be shorter in my opinion. And I haven't done enough error handling. The only instance in which I use a try-except is in my functional testing,
and I've also returned null (which should be avoided) here:

    public Product findByID(String id) {
            for (Product products : productData) {
                if (products.getProductId().equals(id)) return products;
            }
            return null;
        }
After learning from this module, following the tutorial, and doing the exercises, I feel that I still have to keep improving my programming skills.


<h3>Reflection 2</h3>
<ol>
<li> Writing the unit test was an interesting experience. Previously, I hadn't understood much and felt like I wasn't able to make my own tests,
but after doing the exercise, I understand it a little more.
I think the amount of unit tests needed in a class depends on the complexity of the class, the complexity of the methods,
and the connections between the methods. I don't think 100% code coverage guarantees
that the code is free of errors or bugs. Code coverage means 100% of your lines
have been tested, but logical errors may still occur.</li>
<li>I think that keeping the same setup procedures and instance variables
for the new test suite could make the code easy to understand if it's relatively
simple, because it uses a structure we're already familiar with from before. However,
it could be too duplicative or redundant. I think a good way to handle that issue would
be to use a shared superclass so that code can be reused and we can make separate test suits
with similar variables.</li>
</ol>
