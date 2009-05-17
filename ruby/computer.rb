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
    @str = "\tProcessor #{cores} #{type}.\n"
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
  def load aStream
#    @result = handle_computer(eval(aStream))
    @result = handle_computer(aStream)
  end

  def handle_computer sexp
    check_head :computer, sexp
    processor = handle_processor(sexp[1])
    disks = sexp[2..-1].map{|e| handle_disk e}
    return Computer.new(processor, *disks)
  end

  def handle_processor sexp
    check_head :processor, sexp
    check_arg_keys sexp[1..-1], [:cores, :type]
    return Processor.new(select_arg(:cores, sexp),
                         select_arg(:type, sexp))
  end
  def handle_disk sexp
    check_head :disk, sexp
    check_arg_keys sexp[1..-1], [:size, :speed, :interface]
    return Disk.new(select_arg(:size, sexp),
                    select_arg(:speed, sexp), 
                    select_arg(:interface, sexp))
  end
  def select_arg key, sexps
    assoc = sexps.assoc(key)
    return assoc.nil? ? nil : assoc[1]
  end

  def check_arg_keys args, validKeys
    keys = args.map{|e| e[0]}
    bad_keys = keys - validKeys
    raise "incorrect keys:  #{bad_keys.join ', '}" unless bad_keys.empty? 
  end
  def check_head expected, array
    raise "error: expected #{expected}, got #{array.first}" unless array.first == expected
  end
end

print  ListOnlyBuilder.new.load(
[:computer, 
 [:processor, 
  [:cores, 2,], 
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
