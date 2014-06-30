#!/usr/bin/env bash

# this little script will start up the daemons if they're not running - the uima annotator will be more configurable
# so how do we tell if they're running? Let's say we do this: first rope in our execute path extension
#source /uufs/chpc.utah.edu/sys/pkg/metamap/linux_2011/etc/metamap.sh
# THIS SCRIPT IS FOR THE 2012 VERSION
source /uufs/chpc.utah.edu/sys/pkg/metamap/linux_2012/etc/metamap.sh

# ps aux because somebody else may have run it... and make sure we don't catch the grep itself as the tagger.
# ASSUME THAT IF THERE'S A TAGGER SERVER RUNNING IT'S the right one. Cross fingers.
posval=`ps aux | grep taggerServer | grep -v grep | wc -l`
if [[ "$posval" -eq "0" ]]
then
	echo "No tagger server running - running it" 1>&2
	uupostctl start 1>&2
	# zonk a bit, let daemons spin up
	echo "sleeping for a few seconds to let daemon start" 1>&2
	sleep 2
else
	echo "Tagger server is running!" 1>&2
fi
 
# Then the same for wsd - maybe we'll want a config thing that says if we want wsd at all.
wsdval=`ps aux | grep DisambiguatorServer | grep -v grep | wc -l`
if [[ "$wsdval" -eq "0" ]]
then
	echo "No wsd server running - running it" 1>&2
	uuwsdctl start 1>&2
	# zonk a bit, let daemons spin up
	echo "sleeping for a few seconds to let daemon start" 1>&2
	sleep 2
else
	echo "WSD server is running!" 1>&2
fi

#then finally for the metamap server itself. HARDCODED TO 2012 VERSION! 
# this will get 2 lines if it's running, the script and the binary. The stopping-script will want the binary, which we can fetch with
# | grep BINARY, I suppose.
mmsrvval=`ps aux | grep mmserver | grep -v grep | wc -l`
if [[ "$mmsrvval" -eq "0" ]]
then
	echo "No metamap server running - running it REDIRECTING TO /dev/null" 1>&2
	mmserver12 &> /dev/null &
	# zonk a bit, let daemons spin up
	echo "sleeping for a few seconds to let daemon start" 1>&2
	sleep 2
else
	echo "metamap server is running!" 1>&2
fi

