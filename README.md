<h2>Modul Advanced Programming</h2>
<h3>Athira Reika | 2206031422 | Pemrograman Lanjut B</h3>

<a href=#tut1>Tutorial 1</a><br>
<a href=#tut2>Tutorial 2</a><br>
<a href=#tut3>Tutorial 3</a>

<h3 id="tut1">Tutorial 1</h3>

<h5>Reflection 1</h3>
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


<h5>Reflection 2</h3>
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

<h3 id ="tut2">Tutorial 2</h3>
<h5>Reflection</h3>
<ol>
<li>Beberapa isu dalam kode saya sebelumnya adalah bahwa
banyak modifier untuk unit test saya (pada method-method
dan classnya sendiri) adalah public, jadi saya
menghapus modifier tersebut agar default. Di kode saya juga
terdapat beberapa import yang tidak terpakai dalam kodenya,
jadi saya hilangkan. Sebelumnya, saya menggunakan Thread.sleep()
di functional test untuk menunggu elemen-elemen halaman muncul
sebelum mencoba berinteraksi dengan elemen-elemen tersebut, tetapi waktu
loading di mesin yang berbeda akan berbeda, sehingga bisa saja ada
waktu yang terbuang untuk mesin yang lebih cepat, jadi saya hilangkan juga.
Saya juga menggantikan penggunaan field injection di ProductController.java
dan ProductServiceImpl.java.</li>
<li> Menurut saya, workflow untuk proyek saya sudah memenuhi definisi CI/CD.
Pada sisi Continuous Integration, sudah ada workflow
yang akan melakukan scanning dan testing terhadap kode saya setiap
saya melakukan integrasi yaitu merging dari branch lain ke branch main. Workflow
tersebut akan mengecek apabila proyek saya bermasalah setelah diintegrasikan.
Pada sisi Continuous Development, deployment proyek saya di Koyeb sudah dilakukan secara continuous.
Hal tersebut berarti aplikasi yang sudah saya rilis akan diperbarui setiap
ada update pada branch main di repositori proyek saya.</li>
</ol>

<h3 id="tut3">Tutorial 3</h3>
<ol>
<li>Berikut SOLID principles yang sudah saya terapkan dalam kode saya:
<ol>
<li> Single Responsibility Principle: Masing-masing kelas bertanggung jawab atas satu hal.
Sebelumnya, ProductController mencakup CarController, tetapi sudah saya pisah.
Selain itu, constructor Product juga memiliki tugas untuk meng-assign UID untuk
objek Product yang akan dibuat, tetapi fitur itu sudah diserahkan ke method create
di ProductRepository.</li>
<li>Open Closed Principle: Untuk menambah fitur pada program, bisa langsung melakukan
extend dan tidak harus memodifikasi kode lama. Contoh: CarController menggunakan interface
CarService, bukan CarServiceImpl.</li>
<li>Liskov Substitution Principle: CarService di CarController dapat digantikan dengan CarServiceImpl.</li>
<li>Interface Segregation Principle: ProductServiceImpl dan CarServiceImpl tidak terpaksa mengimplementasikan
method dari interface yang mereka implement yang tidak perlu mereka gunakan.</li>
<li>Dependency Inversion Principle: Sebelumnya, CarController menggunakan CarServiceImpl. tetapi
diubah agar dia menggunakan CarService (interface) yang diimplementasikan oleh CarServiceImpl.</li>
</ol>
</li>
<li>Advantages: Secara umum, mengikuti SOLID principles meningkatkan maintainability
dari suatu program. SRP memastikan bahwa setiap kelas memiliki fungsinya sendiri, sehingga
mudah untuk membuat test case dan melakukan debugging karena apabila ada error, kita dapat tahu
persis di mana letaknya. OCP membuat program lebih scalable karena kita dapat menambahkan fitur tanpa
memodifikasi kode yang sudah ada (yang berpotensi untuk mengakibatkan error yang sebelumnya tidak ada). LSP
memastikan bahwa subclass yang sudah ada memiliki sifat yang sama dengan parentnya jadi tidak ada error. ISP memastikan
bahwa suatu kelas tidak harus mengimplementasikan method dari interfacenya yang tidak terpakai, meningkatkan readibility.
DIP memastikan bahwa modul high-level tidak bergantung pada modul low-level, tetapi keduanya bergantung pada abstraksi seperti interface sehingga
perubahan pada modul low-level tidak akan berpotensi merusak modul high-level. Contoh: Jika ada error di CarController, saya akan tahu di mana untuk
melakukan debugging karena fitur-fiturnya berada di kelas sendiri, yaitu CarController itu sendiri (SRP).</li>
<li>Disadvantages: Sebaliknya, tidak mengikuti SOLID principles menurunkan maintainability dan readibility dari sebuah kode.
Contohnya, apabila ada interface bernama Service yang mengandung semua method yang seharusnya di CarService dan ProductService, implementasinya di CarServiceImpl
harus mengimplementasikan method seperti <code>public Product create(Product product);</code> yang tidak digunakan.</li>
</ol>
