class Computer 
  def initialize processor, *disks
    @str = "Computer #{processor} #{disks.join ' '}\n";
  end
  def to_s 
    @str
  end
end

class Processor 
  def initialize cores, type 
    @str = "\tProcessor #{cores.join('..')} #{type}.\n"
  end
  def to_s 
    @str
  end
end

class Disk 
  def initialize size, speed=nil, interface=nil
    @str = "\tDisk #{size} #{interface} #{speed}.\n"
  end
  def to_s 
    @str
  end
end

class ListOnlyBuilder 
  def handle sexp 
    return sexp unless sexp.is_a? Array 
    first=sexp.first;
    return first unless first.is_a? Symbol
    fun = first.to_s
    args = sexp[1..-1].map{|s| handle s}
    puts ": #{fun} (#{args.join(', ')}) "
    self.send("handle_"+fun, *args)
  end

  def method_missing methodname, *args 
    return args;
  end

  def handle_computer processor, *disks
    return Computer.new(processor, *disks)
  end

  def handle_processor cores, type
    return Processor.new(cores,type)
  end
  def handle_disk size, speed=nil, interface=nil
    return Disk.new(size, speed, interface)
  end
end

print  ListOnlyBuilder.new.handle(
[:computer, 
 [:processor, 
  [:cores, 2, 3], 
  [:type, :i386]
 ],
 [:disk, 
  [:size, 150]
 ],
 [:disk, 
  [:size, 75], 
  [:speed, 7200], 
  [:interface, :sata]
 ]
]
).to_s
