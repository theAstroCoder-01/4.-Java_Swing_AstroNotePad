# Java_Swing_AstroNotePad

<!DOCTYPE html>
<html>

<body>

<h1>AstroNotePad</h1>

<p><strong> AstroNotePad </strong> is a fully functional text editor called "The Astro Notepad!" built using Java Swing. It provides basic text editing features such as creating, opening, saving, editing, undoing/redoing changes, cutting, copying, pasting text, and even counting words..
  
<h2>Key Components</h2>

<ol>
   <li><a href="#classes">Main Class</a></li>
</ol>

<h2>Key UI Elements</h2>

<ol>
        <ul>
            <li><a href=“#textarea”>Text Area</a></li>
            <li><a href=“#menubar”>Menu Bar</a></li>
            <li><a href=“#statusbar”>Status Bar</a></li>
            <li><a href=“#scrollpane”>ScrollPane</a></li>
        </ul>
    </li>
</ol>

<h2>Menus and Their Actions</h2>

<ol>
        <ul>
            <li><a href=“#filemenu”>File Menu</a></li>
            	<li><a href=“#new”>New</a></li>
            	<li><a href=“#open”>Open</a></li>
            	<li><a href=“#save”>Save</a></li>
            	<li><a href=“#saveas”>Save As</a></li>
            	<li><a href=“#exit”>Exit</a></li>
<h1></h1>
		<li><a href=“#editmenu”>Edit Menu</a></li>
            	<li><a href=“#undo/redo”>Undo/Redo</a></li>
            	<li><a href=“#cut/copy/paste/selectall”>Cut/Copy/Paste/SelectAll</a></li> 
<h1></h1>
		<li><a href=“#helpmenu”>Help Menu</a></li>
            	<li><a href=“#word/count”>Word Count</a></li>
            	<li><a href=“#about”>About</a></li> 

        </ul>
    </li>
</ol>

<h2>Event Handling</h2>

<ol>
        <ul>
        </ul>
    </li>
</ol>





<h2 id=“mainclass”>Main Class</h2>

<h3 id=“main class”>Main Class</h3>
<p>The <code>AstroNotePad</code> class inherits from JFrame (the main window component in Swing).</p>
<p>Implements <code>ActionListener</code> to handle menu items actions.</p>


<h2 id=“keyuielements”>Key UI Elements</h2>

<h3 id=“textarea”>Text Area</h3>
<p>The main component where the user types the text.</p>

<h3 id=“menubar”>Menu Bar</h3>
<p>Contains three menus—File, Edit, and Help, with relevant actions.</p>

<h3 id=“statusbar”>Status Bar</h3>
<p>Displays the current line and column number of the text cursor.</p>

<h3 id=“scrollpane”>ScrollPane</h3>
<p>Allows scrolling through the text area if it exceeds the window size.</p>


<h2 id=“keyuielements”>Menus and Their Actions</h2>

<h3 id=“filemenu”>FILE MENU</h3>

<h3 id=“new”>New</h3>
<p>Clears the text area and starts a new document.</p>

<h3 id=“open”>Open</h3>
<p>Opens a file using a file chooser and reads its content into the text area.</p>

<h3 id=“save”>Save</h3>
<p>Saves the content of the text area to the current file.</p>

<h3 id=“saveas”>Save As</h3>
<p>Allows the user to save the document under a different name or location.</p>

<h3 id=“exit”>Exit</h3>
<p>Closes the application.</p>

<h3 id=“eitmenu”>EDIT MENU</h3>

<h3 id=“undo/redo”>Undo/Redo</h3>
<p>Uses an UndoManager to manage undo/redo operations.</p>

<h3 id=“cut/copy/paste/selectall”>Cut/Copy/Paste/Select All</h3>
<p>Standard text operations provided by JTextArea.</p>

<h3 id=“helpmenu”>HELP MENU</h3>

<h3 id=“wordcount”>Word Count</h3>
<p>Shows the word count in the current document..</p>

<h3 id=“aboutl”>About</h3>
<p>Shows a pop-up with information about the editor.</p>

</body>

</html>

















