class Event {
      String name
      String id
}

header =  """
#include "sm.h" 
#include "sm-pop.h"

void build_machine() {
"""

footer = """
}
"""
def formatEvents(events) {
    events.collect {
"""    declare_event("${it.name}", "${it.id}");
"""
    }  
}

def generate(lines) {
  output=new PrintWriter(System.out)
  output << lines.flatten().join()
  output.flush()
}

events = [
new Event([name:"doorClosed",id:"D1CL"]),
new Event([name:"drawOpened",id:"D2OP"])]

generate([
header,
formatEvents(events),
footer
])
